KindEditor.plugin('hello', function(K) {
        var editor = this, name = 'hello';
        editor.clickToolbar(name, function() {
                editor.insertHtml('���');
        });
});