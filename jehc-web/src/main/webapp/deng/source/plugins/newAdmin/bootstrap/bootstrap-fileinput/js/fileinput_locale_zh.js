/*!
 * FileInput Spanish (Latin American) Translations
 *
 * This file must be loaded after 'fileinput.js'. Patterns in braces '{}', or
 * any HTML markup tags in the messages must not be converted or translated.
 *
 * @see http://github.com/kartik-v/bootstrap-fileinput
 *
 * NOTE: this file must be saved in UTF-8 encoding.
 */
(function ($) {
    "use strict";
    $.fn.fileinput.locales.es = {
    		fileSingle: '单个文件',
            filePlural: '多个文件',
            browseLabel: '选择文件 &hellip;',
            removeLabel: '删除文件',
            removeTitle: '删除选中文件',
            cancelLabel: '取消',
            cancelTitle: '取消上传',
            uploadLabel: '上传',
            uploadTitle: '上传选中文件',
            msgSizeTooLarge: '文件 "{name}" (<b>{size} KB</b>) 超过允许的最大上传大小 <b>{maxSize} KB</b>. 请重新上传!',
            msgFilesTooLess: '文件数量必须大于 <b>{n}</b> {files} ，请重新上传！',
            msgFilesTooMany: '所选择的上传文件 <b>({n})</b> 超过最大允许限制 <b>{m}</b>. 请重新上传!',
            msgFileNotFound: '文件 "{name}" 未找到!',
            msgFileSecured: '安全限制阻止读取文件 "{name}".',
            msgFileNotReadable: '文件 "{name}" 不可读.',
            msgFilePreviewAborted: '文件预览失败 "{name}".',
            msgFilePreviewError: '读取文件时出错 "{name}".',
            msgInvalidFileType: '文件类型无效 "{name}". 必须为 "{types}" 文件类型才能被支持.',
            msgInvalidFileExtension: '文件类型无效 "{name}". 必须为 "{extensions}" 文件类型才能被支持.',
            msgValidationError: '上传文件失败',
            msgLoading: '加载文件 {index} 到 {files} &hellip;',
            msgProgress: '加载文件 {index} 到 {files} - {name} - {percent}% 已完成.',
            msgSelected: '选中{n}个文件',
            msgFoldersNotAllowed: 'Drag & drop files only! {n} folder(s) dropped were skipped.',
            dropZoneTitle: 'Drag & drop files here &hellip;'
    };

    $.extend($.fn.fileinput.defaults, $.fn.fileinput.locales.es);
})(window.jQuery);
