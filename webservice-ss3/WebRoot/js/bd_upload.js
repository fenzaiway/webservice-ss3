	var myEditorImage;
	var d;
	function upImage()
	{
		d = myEditorImage.getDialog("insertimage");
		d.render();
		d.open();
	}
	myEditorImage = new UE.ui.Editor();
	myEditorImage.render('myEditorImage');
	myEditorImage.ready(function()
	{
		myEditorImage.setDisabled();
		myEditorImage.hide();
		myEditorImage.addListener('beforeInsertImage',function(t,arg)
		{
			handleCallBack(arg);
		});
	});