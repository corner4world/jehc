
/* DOCUMENT LOAD */
jQuery(document).ready(function( $ ) {	
		
	//**********************************
	//NAV MENU
	$('.main-navigation li').hover(function() {
		var subMenu = $(this).find('ul:first');
		$(this).siblings().find('a').removeClass("selected");
		//if has sub menu
		if(subMenu.length) {
			$(this).find('a').eq(0).addClass("selected");
			subMenu.show();
		}
	}, function(e) {  //hover out
		var subMenu = $(this).find('ul:first');
		subMenu.hide();
		$(this).find('a').eq(0).removeClass("selected");
	});
	//NAV MENU
	//**********************************	
	
	
	// ПРИ ИЗМЕНЕНИИ РАЗМЕРА ОКНА
	$(window).resize(function(){ 					
			
         if($("html").width() < 920){
			$("select.menu").show();
			$(".main-navigation > ul ").hide();
		 }
		  else{
			$("select.menu").hide();
			$(".main-navigation > ul ").show();		  
		  }	
	 });	
	
	
	// MOBIL NAV MENU - SELECT LIST
	//**********************************
	/* Clone our navigation */
	var mainNavigation = $('.main-navigation > ul').clone();
	
	/* Replace unordered list with a "select" element to be populated with options, and create a variable to select our new empty option menu */
	$('.main-navigation').prepend('<select class="menu"></select>');
	var selectMenu = $('select.menu');
	$(selectMenu).append('<option>'+"Меню"+'</option>');
	
	/* Navigate our nav clone for information needed to populate options */
	$(mainNavigation).children('li').each(function() {
	
		 /* menu - LEVEL 1 */
		 $(selectMenu).append(generateSelectLink($(this), ''));
		
		 /* menu - LEVEL 2 */
		 if ($(this).children('ul').length > 0) {
				$(this).children('ul').children('li').each(function() {
		
				/* Append this option to our "select" */
				$(selectMenu).append(generateSelectLink($(this), ' » '));
			   
			   /* menu - LEVEL 3 */
			   if ($(this).children('ul').length > 0) {
					$(this).children('ul').children('li').each(function() {
				
					   /* Append this option to our "select" */
					   $(selectMenu).append(generateSelectLink($(this), ' »» '));
					   
					   
					   /* menu - LEVEL 4 */
					   if ($(this).children('ul').length > 0) {
							$(this).children('ul').children('li').each(function() {
						
							   /* Append this option to our "select" */
							   $(selectMenu).append(generateSelectLink($(this), ' »»» '));
							   
							});
						 }
					   
					});
				 }
			   
			});
		 }
	});
	
	function generateSelectLink(li, prefix) {
		var navLink = li.children('a'); 
		if(navLink.length) {
			return '<option value="' + navLink.attr('href') + '"> ' + prefix + navLink.text() + '</option>';
		}
	}
	
	/* When our select menu is changed, change the window location to match the value of the selected option. */
	$(selectMenu).change(function() {
		var url = this.options[this.selectedIndex].value;
		if(url != "") {
			location = this.options[this.selectedIndex].value;
		}
	});
	
	 if($("html").width() < 920){
		$("select.menu").show();
		$(".main-navigation > ul ").hide();
	 }
	  else{
		$("select.menu").hide();
		$(".main-navigation > ul ").show();		  
	  }		
	//**********************************

	
	
	//**********************************
	// CODE PRETTIFY
	if($('.prettyprint').length) {
    	window.prettyPrint && prettyPrint();
	}
	//**********************************
		
	
});
/* DOCUMENT LOAD */
