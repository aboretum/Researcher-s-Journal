<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>

<script src="./resources/js/main.js"></script>

    <script>
    function startTime() {
        var today=new Date();
        var h=today.getHours();
        var m=today.getMinutes();
        var s=today.getSeconds();
        m = checkTime(m);
        s = checkTime(s);
        document.getElementById('time').innerHTML = "Last Updated at: " + h+":"+m+":"+s;
        //var t = setTimeout(function(){startTime()},500);
    }

    function checkTime(i) {
        if (i<10) {i = "0" + i};  // add zero in front of numbers < 10
        return i;
    }
    </script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
   
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
   

    <title>The personal profile</title>

    <!-- Bootstrap Core CSS -->
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom Fonts -->
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
    
    <!-- Custom Fonts -->
    <link href="./resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Custom CSS -->
    <link href="./resources/css/sb-admin.css" rel="stylesheet">
    <link href="./resources/css/layout.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body onload="startTime();">
<div id="wrapper">

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" style="font-size:24px" href="#">Researcher's Journal</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
        <li><a href="/app/">Main Page</a></li>
        <li><a href="GroupInfo">Group Information</a></li>
         <li class="active"><a href="PersonalPage">Personal Profile</a></li>
        <li><a href="about.html">About</a></li>
       
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      </ul>
    </div>
  </div>
  
  
  	 <div class="container-fluid">
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li >
                        <a href="index.html"><i class="icon-file-text-alt"></i> Textual Findings</a>
                    </li>
                    <li >
                        <a href="ResultFigureUpload"><i class="fa fa-fw fa-bar-chart-o"></i> Result Figure Upload</a>
                    </li>
                    <li>
                        <a href="SingleFileUpload"><i class="icon-folder-open-alt"></i> Single File Upload</a>
                    </li>
                    <li>
                        <a href="VideoUpload"><i class="fa fa-fw fa-edit"></i> Research Video Upload</a>
                    </li>
                    
                    
                </ul>
            </div>
            
    	</div>
</nav>
    
        
        

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                
                   <div class="row">
                 	
                 	<div class="col-lg-3" style="font-size:16px">
                 		 
                 		   <div class="well">
                    <p><i class="icon-user icon-large"> </i> User: ${user.userName}</p>
                </div>	
    						
                 	</div>
                 	
                 	<div class="col-lg-3" style="font-size:16px" >
                 		 
                 		<div class="well">
                    		<p><i class="icon-beaker icon-large"> </i> Title: ${user.member_title}</p>
                		</div>	
    						
                 	</div>
                 	
                 	<div class="col-lg-3" style="font-size:16px">
                 		 
                 		    <div class="well">
                    <p><i class="icon-group icon-large">  </i>  Member of: ${user.userGroup}</p>
                </div>	
    						
                 	</div>
                 	
                 	<div class="col-lg-3" style="font-size:16px">
                 		 
                 		   <div class="well">
                    <p><i class="icon-globe icon-large"> </i> Area: ${userGroup.groupArea} </p>
                </div>	
    						
                 	</div>
                 </div>
                 
                <br>
               
                <hr>
                <h4> Your personal documents </h4>
                <br/>
                
              <c:forEach items="${display.docs}" var="document">					
				
                <div class="row">
                    <div class="col-lg-10 ">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    
                                    <div class="col-xs-9 text-left">
                                        <div>Uploaded by ${document.docAuthor}</div>
                                        <div>Description: ${document.docName}</div>
                                    </div>
                                    
                                     <div class="col-xs-3 text-right">
                                     	<div class="btn-group">
  												<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
   													 <span class="glyphicon glyphicon-cog"></span>  Private:${document.docPrivate}
  												</button>
  													<ul class="dropdown-menu" role="menu">
    													<li><a href="ToggleDocPrivacy?username=${user.userName}&doc_url=${document.docUrl}&doc_id=${document.docID}&doc_original_privacy=${document.docPrivate}&doc_privacy=true">Make Private</a></li>
    													<li><a href="ToggleDocPrivacy?username=${user.userName}&doc_url=${document.docUrl}&doc_id=${document.docID}&doc_original_privacy=${document.docPrivate}&doc_privacy=false">Make Public</a></li>
  													</ul>
										</div>
                                        
                                    </div>
                                    
                                </div>
                            </div>
                            <a href="#">
                                <div class="panel-footer">
      
                                    <span class="pull-left" onclick="javascript:load_content('pnuts');">
                                    	<img class="thumb-nail" src=".${document.docUrl}" width ="720" height="420" />
									</span>
                                    <span class="pull-right"></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    
                </div>
                <br><br>
             </c:forEach>
             
            </div>
            <hr>
            <div id = "content_div" hidden=true style="border:1px solid black;height:40%;overflow:auto">
                <p id="page_content"> 

                </p>
        
    </div>
    <!-- /#page-wrapper -->

    
    </div>
    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="./resources/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="./resources/js/bootstrap.min.js"></script>

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>
	<script src="./resources/js/dragdrop.js"></script>
</body>
</html>