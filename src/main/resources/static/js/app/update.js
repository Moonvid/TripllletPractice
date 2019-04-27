var update = {
    init : function () {
        var _this = this;
        $('#btn-edit').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            id: $('#id').val(),
            title: $('#title').val(),
            writer: $('#writer').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/update',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            location.href="/";
        }).fail(function (error) {
            alert(error);
        });
    }

};

