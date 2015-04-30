
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>

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
 
  

    <title>Result Figures</title>
    

	
    <!-- Bootstrap Core CSS -->
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
    
	<!-- Bootstrap Date Time Picker CSS -->
	<link href="./resources/css/bootstrap-datetimepicker.css" rel="stylesheet" media="screen">
	<!-- Add fancyBox -->
	<link rel="stylesheet" href="./resources/fancybox/source/jquery.fancybox.css?v=2.1.5" type="text/css" media="screen" /> 
	 
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
        <li><a href="PersonalPage">Personal Profile</a></li>
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
                    <li class="active">
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
                <div align="right" id="time" style="font-size:15px"></div><hr>
                
                 	 <div class="row">
                   
                   
            
                    <div class="col-lg-10">
                        <h2>Figure documents</h2>
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>File Name</th>
                                        <th>View</th>
                                        <th>Uploaded by</th>
                                        <th>Upload Date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${files}" var="document">
                                    	<tr>
                                        	<td>${document.docName}</td>
                                        	
                                        	<td><a class="fancybox" rel="group" href=".${document.docUrl}"><i class="icon-eye-open"></i>  View Image</a></td>
                                        	<td>${document.docAuthor}</td>
                                        	<td>${document.docDate}</td>
                                    	</tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
        	</div>
        	</div>
    </div>
    <!-- /#page-wrapper -->

    
    </div>
    <!-- /#wrapper -->
	
	
    
	
	<script type="text/javascript" src="./resources/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="./resources/fancybox/source/jquery.fancybox.pack.js?v=2.1.5"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="./resources/js/bootstrap.min.js"></script>
	 <!-- Bootstrap Date Time Picker JavaScript -->
	<script type="text/javascript" src="./resources/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="./resources/js/locales/bootstrap-datetimepicker.ar.js" charset="UTF-8"></script>
	<script type="text/javascript">
	$('#form_date').datetimepicker({
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
</script>
	
	
	<script src="./resources/js/vendor/jquery.ui.widget.js"></script>
	<script src="./resources/js/jquery.iframe-transport.js"></script>
	<script src="./resources/js/jquery.fileupload.js"></script>
	
	
	<script src="./resources/js/myuploadfunction.js"></script>
	
    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>
    
     <script type="text/javascript">
	$(document).ready(function() {
		$(".fancybox").fancybox();
	});
	</script>
</body>
</html>