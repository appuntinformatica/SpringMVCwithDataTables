$(document).ready(function() {
    var fineUploaderMessages;
    var fineUploaderStatus;
    var fineUploaderText;
    $.get( '/fine-uploader/i18n/' + lang + '.js', function( data ) {
        eval(data);
        $.get( '/fine-uploader/i18n/template.' + lang + '.html', function( data ) {
            $('#qq-template').html( data );
            var fineUploader = new qq.FineUploader({
                element: document.getElementById("fine-uploader"),
                template: 'qq-template',                
                request: {
                    endpoint: '/upload',
                    inputName: 'file',
                    filenameParam: 'filename'
                },
                thumbnails: {
                    placeholders: {
                        waitingPath: '/fine-uploader/placeholders/waiting-generic.png',
                        notAvailablePath: '/fine-uploader/placeholders/not_available-generic.png'
                    }
                },/*
                validation: {
                    allowedExtensions: ['jpeg', 'jpg', 'txt', 'jar'],
                    itemLimit: 3,
                    sizeLimit: 5242880 // 50 kB = 5 * 1024 * 1024 bytes
                },*/
                messages: fineUploaderMessages,
                text: fineUploaderText
            });
            fineUploader.status = fineUploaderStatus;
        });        
    });
    
    $.get( '/DataTables/i18n/columns.' + lang + '.js', function( data ) {
        var columms = eval(data);
        
    
    
   
    
    var table = $('#datatable').DataTable({
        'ajax' : '/data/users',
        'serverSide' : true,
        'language': {
            "url": "/DataTables/i18n/DataTables." + lang + ".json"
        },
        columns : [ {
            data : 'id',
            title : columms['id']
        }, {
            data : 'mail',
            title : columms['email']
        }, {
            data : 'role',
            title : columms['role']
        }, {
            data : 'status',
            title : columms['status']
        }, {
            data : 'home.town',
            title :  columms['town'],
            render : function(data, type, row) {
                if (row.home) {
                    return row.home.town;
                }
                return '';
            }
        }, {
            // add another column not persisted on the server-side
            data : 'anothercolumn',
            title :  columms['other'],
            // order is not available
            orderable : false,
            // yet filter should be available through the method
            // findAll(DataTablesInput input, Specification<T>
            // additionalSpecification)
            searchable : false,
            render : function(data, type, row) {
                if (row.role && row.status) {
                    return row.role + ' and ' + row.status;
                }
                return '';
            }
        } ]
    });

    $('select#role_selector').change(function() {
        var filter = '';
        $('select#role_selector option:selected').each(function() {
            filter += $(this).text() + "+";
        });
        filter = filter.substring(0, filter.length - 1);
        table.columns(2).search(filter).draw();
    });

    $('select#status_selector').change(function() {
        var filter = '';
        $('select#status_selector option:selected').each(function() {
            filter += $(this).text() + "+";
        });
        filter = filter.substring(0, filter.length - 1);
        table.columns(3).search(filter).draw();
    });
    
     });
});