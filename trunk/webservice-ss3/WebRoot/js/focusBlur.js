jQuery.fn.extend({
    	FocusBlur: function() {
        $(this).live('focus',function(){
            if(!$(this).data('old'))
                $(this).data('old', $(this).val());
            
            if($(this).val()==$(this).data('old'))
                $(this).val('');
        });
        $(this).live('blur',function(){
            if($(this).val()=='')
                $(this).val($(this).data('old'));
        });
        
    	}
	});

