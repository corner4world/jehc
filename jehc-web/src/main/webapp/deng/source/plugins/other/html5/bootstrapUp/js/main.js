$(function () {

  'use strict';

  var console = window.console || { log: function () {} },
	  $alert = $('.docs-alert'),
	  $message = $alert.find('.message'),
	  showMessage = function (message, type) {
		$message.text(message);
		if (type) {
		  $message.addClass(type);
		}
		$alert.fadeIn();
		setTimeout(function () {
		  $alert.fadeOut();
		}, 3000);
	  };
  function CropAvatar($element) {
    this.$container = $element;
	this.$loading = this.$container.find('.loading');
    this.$avatarForm = this.$container.find('.avatar-form');
	this.$avatarSave = this.$avatarForm.find('.avatar-save');
    this.$avatarBtns = this.$avatarForm.find('.avatar-btns');
    this.init();
    console.log(this);
  }
  CropAvatar.prototype = {
    constructor: CropAvatar,
    support: {
      fileList: !!$('<input type="file">').prop('files'),
      blobURLs: !!window.URL && URL.createObjectURL,
      formData: !!window.FormData
    },
    init: function () {
      this.addListener();
    },
    addListener: function () {
      this.$avatarForm.on('submit', $.proxy(this.submit, this));
	  $('.avatar-save').on('click', $.proxy(this.submit, this));
    },
    submit: function () {
	  console.info('--------------'+$('#inputImage').val());
      if ($('#inputImage').val() == null) {
		msgTishi("无法提交，原因是您还没有选择图片");
        return false;
      }
      if (this.support.formData) {
        this.ajaxUpload();
        return false;
      }
    },
   
	//销毁图片
    stopCropper: function () {
      if (this.active) {
        this.$img.cropper('destroy');
        this.$img.remove();
        this.active = false;
      }
    },
	//执行提交
    ajaxUpload: function () {
      showLoading("正在上传中，请稍后...");
      var url = document.getElementById('avatar-form').attributes['action'].value,
          data = new FormData($( "#avatar-form" )[0]),
          _this = this;
	  console.info(data);
      $.ajax(url, {
        type: 'post',
        data: data,
        async: false,  
        cache: false,
        //dataType: 'json',  
        contentType: false,  
        processData: false,  
        beforeSend: function () {
          _this.submitStart();
        },
        success: function (data) {
        	console.info(data);
          _this.submitDone(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
          _this.submitFail(textStatus || errorThrown);
        },
        complete: function () {
          _this.submitEnd();
        }
      });
    },
	//锁住提交按钮
    syncUpload: function () {
      this.$avatarSave.click();
    },
	//开始提交
    submitStart: function () {
      
    },
	//提交成功之后方法
    submitDone: function (data) {
      console.log(data);
      if(data == 0){
      	  msgTishi("上传失败");
      }else{
	      var win = parent.Ext.getCmp('upWin');
	      if (win) {win.close();}
	      msgTishi("上传成功");
      }
    },
    submitFail: function (msg) {
       msgTishi("上传失败");
      var win = parent.Ext.getCmp('upWin');
      if (win) {win.close();}
    },
	//提交结束
    submitEnd: function () {
      hideWaitMsg();
    }
  };
  $(function () {
    return new CropAvatar($('#crop-avatar'));
  });
  (function () {
    var $image = $('.img-container > img'),
        $dataX = $('#dataX'),
        $dataY = $('#dataY'),
        $dataHeight = $('#dataHeight'),
        $dataWidth = $('#dataWidth'),
        $dataRotate = $('#dataRotate'),
        options = {
          // strict: false,
          // responsive: false,
          // checkImageOrigin: false

          // modal: false,
          // guides: false,
          // highlight: false,
          // background: false,

          // autoCrop: false,
          // autoCropArea: 0.5,
          // dragCrop: false,
          // movable: false,
          // resizable: false,
          // rotatable: false,
          // zoomable: false,
          // touchDragZoom: false,
          // mouseWheelZoom: false,

          // minCanvasWidth: 320,
          // minCanvasHeight: 180,
          // minCropBoxWidth: 160,
          // minCropBoxHeight: 90,
          // minContainerWidth: 320,
          // minContainerHeight: 180,

          // build: null,
          // built: null,
          // dragstart: null,
          // dragmove: null,
          // dragend: null,
          // zoomin: null,
          // zoomout: null,

          aspectRatio: 16 / 9,
          preview: '.img-preview',
          crop: function (data) {
            $dataX.val(Math.round(data.x));
            $dataY.val(Math.round(data.y));
            $dataHeight.val(Math.round(data.height));
            $dataWidth.val(Math.round(data.width));
            $dataRotate.val(Math.round(data.rotate));
          }
        };

    $image.on({
      'build.cropper': function (e) {
        console.log(e.type);
      },
      'built.cropper': function (e) {
        console.log(e.type);
      },
      'dragstart.cropper': function (e) {
        console.log(e.type, e.dragType);
      },
      'dragmove.cropper': function (e) {
        console.log(e.type, e.dragType);
      },
      'dragend.cropper': function (e) {
        console.log(e.type, e.dragType);
      },
      'zoomin.cropper': function (e) {
        console.log(e.type);
      },
      'zoomout.cropper': function (e) {
        console.log(e.type);
      }
    }).cropper(options);
    //所有方法
    $(document.body).on('click', '[data-method]', function () {
      var data = $(this).data(),
          $target,
          result;
      if (data.method) {
        data = $.extend({}, data); //克隆一个新的对象
        if (typeof data.target !== 'undefined') {
          $target = $(data.target);

          if (typeof data.option === 'undefined') {
            try {
              data.option = JSON.parse($target.val());
            } catch (e) {
              console.log(e.message);
            }
          }
        }
        result = $image.cropper(data.method, data.option);
        if (data.method === 'getCroppedCanvas') {
          $('#getCroppedCanvasModal').modal().find('.modal-body').html(result);
        }
        if ($.isPlainObject(result) && $target) {
          try {
            $target.val(JSON.stringify(result));
          } catch (e) {
            console.log(e.message);
          }
        }
      }
    }).on('keydown', function (e) {
      switch (e.which) {
        case 37:
          e.preventDefault();
          $image.cropper('move', -1, 0);
          break;
        case 38:
          e.preventDefault();
          $image.cropper('move', 0, -1);
          break;
        case 39:
          e.preventDefault();
          $image.cropper('move', 1, 0);
          break;
        case 40:
          e.preventDefault();
          $image.cropper('move', 0, 1);
          break;
      }
    });
    // 导入图片
    var $inputImage = $('#inputImage'),
        URL = window.URL || window.webkitURL,
        blobURL;
    if (URL) {
      $inputImage.change(function () {
        var files = this.files,
            file;
        if (files && files.length) {
          file = files[0];
		  
          if (/^image\/\w+$/.test(file.type)) {
            blobURL = URL.createObjectURL(file);
            $image.one('built.cropper', function () {
              URL.revokeObjectURL(blobURL); // Revoke when load complete
            }).cropper('reset', true).cropper('replace', blobURL);
			console.info('------------'+$inputImage.val());
			/**邓纯杰注释
            $inputImage.val('');
			**/
          } else {
          	msgTishi('选择的不是图片类型,请选择一个图片文件!');
          }
        }
      });
    } else {
      $inputImage.parent().remove();
    }
    // Options
    $('.docs-options :checkbox').on('change', function () {
      var $this = $(this);
      options[$this.val()] = $this.prop('checked');
      $image.cropper('destroy').cropper(options);
    });
    // Tooltips
    $('[data-toggle="tooltip"]').tooltip();
  }());
});
