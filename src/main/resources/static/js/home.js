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
            'ajax' : '/list',
            'serverSide' : true,
            'language': {
                "url": "/DataTables/i18n/DataTables." + lang + ".json"
            },
            columns : [ {
                data : 'filename',
                title : columms['filename']
            }, {
                data : 'filesize',
                title : columms['filesize']
            }, {
                data : 'datetime',
                title : columms['datetime']
            }]
        });    
    });
});