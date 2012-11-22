<!doctype html>
<html lang="en">
    <head><#include "/back/layout/head.ftl"></head>
    <body>
        <header id="header">
            <hgroup>
                <h1 class="site_title"><a href="index.html">Lefoto Back Manage</a></h1>
                <h2 class="section_title">Album Manage</h2>
                <div class="btn_view_site"><a href="/lefoto/index/show.html">Lefoto</a></div>
            </hgroup>
        </header>
        <!-- end of header bar -->
        <section id="secondary_bar">
            <div class="user">
                <p>Eric (<a href="#">3 Messages</a>)</p>
                <!-- <a class="logout_user" href="#" title="Logout">Logout</a> -->
            </div>
            <div class="breadcrumbs_container">
                <article class="breadcrumbs">
                    <a href="index.html">Website Admin</a>
                    <div class="breadcrumb_divider"></div>
                    <a class="current">AlbumManage</a>
                </article>
                <button class="btn btn-mini btn-primary" onclick="showAlbumCreation()" style="float: right; margin: 4px 20px 0 0">创建相册</button>
            </div>
        </section><!-- end of secondary bar -->
        <#include "/back/layout/aside.ftl">

        <section id="main" class="column">
            <!-- 创建分类 -->
            <article id="albumCreation"class="module" style="display: none;float: right;width: 200px;height: 50px;">
                <form class="submit_link" action="/lefoto/back/album/add.html" method="post">
                    <input type="text" name ="albumName" style="width: 100px;margin-top: 7px;">
                    <input type="submit" value="创建" class="alt_btn">
                </form>
            </article>
            <div class="clear"></div>
            <!-- END创建分类 -->
            <h4 class="alert_info">Welcome to the Album admin panel template, this place will be userd for delivering the target information.</h4>
            

            <article class="module width_full">
                <header><h3 class="tabs_involved">相册管理</h3>
                    <ul class="tabs">
                        <li><a href="#tab1">Posts</a></li>
                        <li><a href="#tab2">Comments</a></li>
                    </ul>
                </header>

                <div class="tab_container">
                    <div id="tab1" class="tab_content">
                        <table class="tablesorter" cellspacing="0"> 
                            <thead> 
                                <tr> 
                                    <th></th> 
                                    <th>相册名</th> 
                                    <th>分类</th> 
                                    <th>创建时间</th> 
                                    <th>操作</th> 
                                </tr> 
                            </thead> 
                            <tbody> 
                                <#list albums as album>
                                <tr>
                                    <td><input type="checkbox"></td>
                                    <td>${album.name}</td>
                                    <td>${album.category_id}</td>
                                    <td>${album.description}</td>
                                    <td>
                                        <input type="image" src="/lefoto/src/plugins/backtemplate/images/icn_edit.png" title="Edit">
                                        <input class="categoryDelete" value="${category.name}" type="image" src="/lefoto/src/plugins/backtemplate/images/icn_trash.png" title="Trash">
                                    </td> 
                                </tr> 
                                </#list>
                            </tbody> 
                        </table>
                    </div><!-- end of #tab1 -->

                    <div id="tab2" class="tab_content">
                        <table class="tablesorter" cellspacing="0"> 
                            <thead> 
                                <tr> 
                                    <th></th> 
                                    <th>Comment</th> 
                                    <th>Posted by</th> 
                                    <th>Posted On</th> 
                                    <th>Actions</th> 
                                </tr> 
                            </thead> 
                            <tbody> 
                                <tr> 
                                    <td><input type="checkbox"></td> 
                                    <td>Lorem Ipsum Dolor Sit Amet</td> 
                                    <td>Mark Corrigan</td> 
                                    <td>5th April 2011</td> 
                                    <td><input type="image" src="/lefoto/src/plugins/backtemplate/images/icn_edit.png" title="Edit"><input type="image" src="/lefoto/src/plugins/backtemplate/images/icn_trash.png" title="Trash"></td> 
                                </tr> 
                                <tr> 
                                    <td><input type="checkbox"></td> 
                                    <td>Ipsum Lorem Dolor Sit Amet</td> 
                                    <td>Jeremy Usbourne</td> 
                                    <td>6th April 2011</td> 
                                    <td><input type="image" src="/lefoto/src/plugins/backtemplate/images/icn_edit.png" title="Edit"><input type="image" src="/lefoto/src/plugins/backtemplate/images/icn_trash.png" title="Trash"></td> 
                                </tr>
                                <tr> 
                                    <td><input type="checkbox"></td> 
                                    <td>Sit Amet Dolor Ipsum</td> 
                                    <td>Super Hans</td> 
                                    <td>10th April 2011</td> 
                                    <td><input type="image" src="/lefoto/src/plugins/backtemplate/images/icn_edit.png" title="Edit"><input type="image" src="/lefoto/src/plugins/backtemplate/images/icn_trash.png" title="Trash"></td> 
                                </tr> 
                                <tr> 
                                    <td><input type="checkbox"></td> 
                                    <td>Dolor Lorem Amet</td> 
                                    <td>Alan Johnson</td> 
                                    <td>16th April 2011</td> 
                                    <td><input type="image" src="/lefoto/src/plugins/backtemplate/images/icn_edit.png" title="Edit"><input type="image" src="/lefoto/src/plugins/backtemplate/images/icn_trash.png" title="Trash"></td> 
                                </tr> 
                                <tr> 
                                    <td><input type="checkbox"></td> 
                                    <td>Dolor Lorem Amet</td> 
                                    <td>Dobby</td> 
                                    <td>16th April 2011</td> 
                                    <td><input type="image" src="/lefoto/src/plugins/backtemplate/images/icn_edit.png" title="Edit"><input type="image" src="/lefoto/src/plugins/backtemplate/images/icn_trash.png" title="Trash"></td> 
                                </tr> 
                            </tbody> 
                        </table>

                    </div><!-- end of #tab2 -->

                </div><!-- end of .tab_container -->

            </article><!-- end of content manager article -->
        </section>
        <script	type="text/javascript">
            function showAlbumCreation(){
                if($("#albumCreation").is(":visible")){
                    $("#albumCreation").fadeOut('slow');
                }else{
                    $("#albumCreation").fadeIn('slow');
                }
            }
        </script>
    </body>
</html>