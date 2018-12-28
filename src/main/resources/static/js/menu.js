$(function(){
    var oP=$('.nav-menu').find('p');
    var oUl=oP.next('ul');
    oUl.hide();
    oP.click(function(){
        var oLi=$(this).parent('li').siblings('li');
       /* oLi.children('p').children('i').addClass('hidden');
        oLi.children('p').children('i:even').removeClass('hidden');*/
        oLi.children('p').children('i').addClass('glyphicon-chevron-down');
        oLi.children('ul').slideUp('slow');
        /*$(this).children('i').toggleClass('hidden');*/
        $(this).children('i').toggleClass('glyphicon-chevron-up');
        $(this).next('ul').slideToggle(300);
    });
    oUl.children('li').children('a').click(function(){
        $(this).parents('.nav-menu').find('.active').removeClass('active');
        $(this).addClass('active');
        var menuName = $(this).attr("data");
        if (menuName != null){
            $(".show").html(menuName+"<span></span>");
        }
    });

})

/*$(document).ready(function () {
    $('#menu').metisMenu();
})*/
