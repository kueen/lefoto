var _CB;
;(function(){
    var doms = {};
    function tempToHTML(id, data, def){
        var dom = doms[id] == undefined ? (doms[id] = document.getElementById(id)) : doms[id]; //缓存dom模版，保存在doms对象中
        var parseObj = dom[id] || (dom[id] = (function(html){
            html = html.replace(/([\'|\\])/gm,"\\$1")   //转义掉 \ 和 '
                        .replace(new RegExp('{([^{}]*)}','gim'), "'+(typeof data[\"$1\"] != 'undefined' ? data[\"$1\"] : '')+'")  //转化为包括变量的字符串
                        .replace(/[\n\r]/gm,'');    //去除回车换行
            html = ["return '", html ,"';"].join('');
            //console.log(html);
            return new Function('data',html);
        })(dom.innerHTML));
        var len = data.length;
        if(len > 0){
            for(var i = 0; i < len ; i++) {
                data[i] = parseObj(data[i]);
            }
        } else data[0] = def || '';
        return data.join('');
        //dom.innerHTML = data.join('');
    }
    window.tempToHTML = tempToHTML;
})()
;(function($){
    //返回顶部start
    var backToTopEle = $('<div class="backToTop"><a href="javascript:void(0);" title="返回顶部"></a></div>')
        .css({ 'display': 'none' })
        .appendTo($("body")).click(function () {
            $("html, body").animate({ scrollTop : 0 }, 300);
        }), backToTopFun = function () {
            var st = $(document).scrollTop();
            (st > 2000) ? backToTopEle.show() : backToTopEle.hide();
        };
    $(window).bind("scroll", backToTopFun);
    //返回顶部end
})(jQuery)
;(function($){
    var getLikeUsersUrl = '/photo/getUpUsers.html';//获取喜欢
    var addPhotoLikeUrl = '/photo/upPhoto.html';//添加喜欢
    var cancelPhotoLikeUrl = '/photo/cancelUpPhoto.html';//取消喜欢
    var addCommentUrl = '/comment/addComment.html';//添加评论
    var loadCommentUrl = '/comment/getLimitComments.html';//获取评论
    var loginUrl = 'login/submit.html';
    var OBJ_IMG = 1;
    var iCur = -1;
    var leAToggle = 0;
    
    //template
    var comment_temp = 'comment_temp';
    
    //@功能参数
    
    var iTimer;
    var queue = 1;
    var _cb = '_CB';
    var atUrl = '/user/searchAtUsers.html';
    
    function loadComment(id,commentRange){ // 加载评论
        var list = commentRange.find('.list');
        list.empty();
        var data = {
            objectId: id,
            objectType: OBJ_IMG
        };
        list.addClass('loading');
        $.get(loadCommentUrl,data,function(response){
            var res = eval('('+response+')');
            list.removeClass('loading').html(tempToHTML(comment_temp, res.data));
        });
    }
    function doAddComment(comment, callback){
        $.post(addCommentUrl,comment, callback);
    }
    function doClick(target) { //图片上TAB点击事件处理
        var parent = target.closest('.tabs'),
            pparent = parent.closest('.tabsWrap'),
            item  = target.closest('.item'),
            loading = parent.find('.loading'),
            likeDiv = parent.find('div.like'),
            shareDiv = parent.find('div.share'),
            itemId = target.attr('rel'),
            num = parseInt(target.text()),
            getLikeUsers = function(){
                loading.show();
                var data = {
                    photoId: itemId
                };
                $.get(getLikeUsersUrl, data, function(response){
                    $(response).appendTo(parent);
                    loading.hide();
                });
            };

        if(target.hasClass('like')) { //喜欢按钮被点击
            if(pparent.height() > 40 && iCur == 1){
                pparent.stop().animate({'height': '40px'},300);
                return false;
            }
            if(likeDiv.length == 0) {
                getLikeUsers();
            } else {
                likeDiv.remove();
                getLikeUsers();
            }
            shareDiv.hide();
            iCur = 1;
        } else if(target.hasClass('share')){ //分享按钮被点击
            if(pparent.height() > 40 && iCur == 2){
                pparent.stop().animate({'height': '40px'},300);
                return false;
            }
            likeDiv.hide();
            shareDiv.show();
            iCur = 2;
        }else if(target.hasClass('comment')) { //评论按钮被点击
            if(pparent.height() > 40){
                pparent.stop().animate({'height': '40px'},300);
            }
            var commentRange = item.find('div.comments');
            if(commentRange.is(':visible')){
                commentRange.hide();
            }else{
                loadComment(itemId, commentRange);
                commentRange.show();
            }
            iCur = 0;
            return false;
        }
        target.closest('.tabsWrap').animate({'height': '80px'}, 300);
        return false;
    }
    
    var doAddPhotoLike = function(target){ //添加喜欢
        var id = target.attr('rel');
        var item = target.closest('.item');
        var likeTab = $('a.like', item);
        var data = {
            photoId:id
        };
        $.post(addPhotoLikeUrl,data,function(response){
            if(response == 'success'){
                target.parent().addClass('isLike');
                target.attr('data-up','1');
                likeTab.html('<span>'+ (parseInt(likeTab.text())+1)+'</span>');
            }else{
                alert(response);
            }
        });
    }
    var doCancelPhotoLike = function(target){ //取消喜欢
        var id = target.attr('rel');
        var item = target.closest('.item');
        var likeTab = $('a.like',item);
        var data = {
            photoId:id
        };
        $.post(cancelPhotoLikeUrl,data,function(response){
            if(response == 'success'){
                target.parent().removeClass('isLike');
                target.attr('data-up','0');
                likeTab.html('<span>'+ (parseInt(likeTab.text())-1)+'</span>');
            }else{
                alert(response);
            }
        });
    }
    var doDown = function(pEle){ //下落
        var pos = pEle.position();
        var startX = pos.left + 25;
        var startY = pos.top + 25;
        var downEle = $('<div class="downEle"></div>').appendTo(pEle.closest('.iMid'))
                    .css({'top':startY+'px','left':startX+'px'})
                    .animate({'top':'+=500px'},1000,function() {
                         downEle.remove();
                    });
                    
    }
    var showSmile = function(_iMid) { //显示微笑
        var le = $('.leMid', _iMid);
        var leA = le.find('.leA');
        var iMid = $(_iMid),
            iMidW = iMid.width(),
            iMidH = iMid.height();
        var eW = le.width(),
            eH = le.height();
        var posL = (iMidW - eW)/2,
            posT = (iMidH - eH)/2;
        le.css({'top': posT + 'px','left': posL + 'px'});
        leAToggle = parseInt(leA.attr('data-up'));
        if(leAToggle){
            le.addClass('isLike');
        } else {
            le.removeClass('isLike');
        }
        le.fadeIn(200);
    };
    var checkLogin = function(){
        if(user == undefined){ $('.rside a.G').click(); return false;}
        return true;
    }
    $(document).delegate('.iMid','mouseenter',function(){ //鼠标进入图片
        var box = $('.tabsWrap', this);
        if(box.height() > 40){

        } else {
            box.stop().animate({'height': '40px'},300);
        }
        showSmile(this);
    }).delegate('.iMid','mouseleave',function(){ //鼠标移出图片
        var box = $('.tabsWrap', this);
        var le = $('.leMid', this);
        if(box.height() > 40){

        }else{
            box.stop().animate({'height': '0px'},300);
        }
        le.hide();
    }).delegate('a.icon', 'click', function() {
        var target = $(this);
        doClick(target);
    }).delegate('li.isList', 'mouseenter', function() { 
        $('ul',this).show();
    }).delegate('li.isList', 'mouseleave', function() { 
        $('ul',this).hide();
    }).delegate('a.leA','click',function(){
        if(!checkLogin()){return;}
        var target = $(this);
        if(leAToggle){
            leAToggle = 0;
            doCancelPhotoLike(target);
        }else{
            leAToggle = 1;
            doAddPhotoLike(target);
        }
    }).delegate('.btn-addComment','click',function(){ //评论按钮被点击
        var target = $(this);
        var item = target.closest('.item');
        var commentTab = $('a.comment', item);
        var wrap = target.closest('.fwrap');
        var list = wrap.siblings('.list');
        var id = target.attr('rel');
        var userId = wrap.find('input[name=userId]').val()
        var textarea = wrap.find('textarea[name=content]');
        var content = textarea.val();
        if(!checkLogin()){return;}
        if($.trim(content).length == 0){ $('.editor', wrap).showWaring('请正确填写'); return ;} 
        
        var comment = {};
        var callback = function(response){ //添加评论
            //if(response.data == 'success'){
            var response = eval('('+response+')');
            textarea.val('');
            console.log(list);
            list.append(tempToHTML(comment_temp, response.data));
            commentTab.html('<span>'+ (parseInt(commentTab.text())+1)+'</span>');
            //} else {
            //    alert(response);
            //}
        };
        comment['content'] = content;
        comment['objectId'] = id;
        comment['objectType'] = 1;
        comment['objectUserId'] = userId;
        doAddComment(comment, callback);
    }).delegate('textarea','keyup',function() {
        var target = $(this);
        _CB = function(response){
            var data = eval(response);
            target.siblings('.atUL').ToHTML(data).show();
        };
        clearTimeout(iTimer);
        iTimer = setTimeout(function(){ 
            var atText = (function getAtText(input){
                var REX = /(\b|\B)[^\s]+(\b|\B)/igm;
                var match = REX.exec(input);
                var lastMatch, atText, atIndex;
                while(match) {
                    lastMatch = match[0];
                    match = REX.exec(input);
                }
                if(lastMatch && (atIndex = lastMatch.lastIndexOf('@')) > -1){
                    atText = lastMatch.substring(atIndex + 1);
                }
                return atText;
            })(target.val());
            var param = atUrl + '?content='+atText+'&callback=' + _cb + queue;
            if(atText){
                $('<script type="text/javascript">var '+_cb+queue+'='+_cb+';'+_cb+(queue - 1)+'=null;</script>').appendTo('body');
                var callbackScript = document.createElement('script');
                callbackScript.src = param;
                document.body.appendChild(callbackScript);
                queue++;
            } else {
                target.siblings('.atUL').hide();
            }
        },500);
    }).delegate('.rside a.G','click',function(){
        var target = $(this);
        target.closest('.rside').find('.login').show();
    }).delegate('.loginB a.cancel','click',function(){
        var target = $(this);
        target.closest('.rside').find('.login').hide();
        //清空登陆框及提示信息
        $('#loginMsg').html('');
        $('#_lf').find('input').val('');
    }).delegate('.loginB a.btn-ok','click',function(){
        var target = $(this);
        var data = $('#_lf').getValueAndToObject('input');
        if(!target.hasClass('dealing')){
            target.addClass('dealing').html('正在登录...');
            $.post(loginUrl,data, function(response){
                var result = eval('(' + response + ')');//注：返回数据类型json，例如{'status':'error','message':'账户或密码错误'}
                if(result.status != 'success'){
                    target.removeClass('dealing').html('确定');
                    $('#loginMsg').html(result.message);
                } else {
                    target.siblings('a.cancel').click();
                    alert("登录成功,需返回用户信息。数据格式：{'status':'success','user':{}}");
                }
            });
        }
    }).delegate('.comLi a.at','click',function(){
        var target = $(this);
        var commentRange = target.closest('.comments');
        var editor = commentRange.find('.editor');
        editor.val(editor.val() + '@' + target.text());
    });
    $('body').click(function(event){
        if($(event.target).closest('.login').length == 0){
            $('.login').hide();
        }
    });
})(jQuery);