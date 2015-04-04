<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

    <title>The main page</title>

    <!-- Bootstrap Core CSS -->
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="./resources/css/simple-sidebar.css" rel="stylesheet">
    <link href="./resources/css/layout.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body onload="startTime();">
	<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" style="font-size:24px" href="#">Researcher's Journal</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
        <li class="active"><a href="/app/">Main Page</a></li>
        <li><a href="GroupInfo">Group Information</a></li>
        <li><a href="about.html">About</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
    <div id="wrapper">
        
        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="#">
                        Upload Documents
                    </a>
                </li>
                <li>
                    <a href="javascript:load_content('java_compiler');">Textual Findings</a>
                </li>
                <li>
                    <a href="javascript:load_content('rails');">Result Figure Upload</a>
                </li>
                <li>
                    <a href="javascript:load_content('hadoop');">Single File Upload</a>
                </li>
                <li>
                    <a href="javascript:load_content('hive');">Research Video Upload</a>
                </li>
                <li>
                    <a href="javascript:load_content('scala');">Your Profile</a>
                </li>
               
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div align="right" id="time" style="font-size:15px"></div><br><hr>
                <div class = "row" style="font-size:15px">
                	<div class="col-lg-3 col-md-6">
                        User: ${user.userName}
                    </div>
                    <div class="col-lg-3 col-md-6">
                        Title: ${user.member_title}
                    </div>
                    <div class="col-lg-3 col-md-6">
                        Member of: ${user.userGroup} 
                    </div>
                    <div class="col-lg-3 col-md-6">
                        Area of research: ${userGroup.groupArea} 
                    </div>
                </div>
                <br><hr>
                <h4>Upload a new Document</h4>
                    <form method="post" action="AddDocument" name="submit" enctype="multipart/form-data" >
                    <div class="row">
                    	<div class="col-md-3 "><input type="file" name="fileField" ></div>
                    	<div class="col-md-3 "><input type="submit" class="btn btn-primary" name="submit" value="Submit" ></div>
                    </div>
                        
                        
                    </form> 
                    
                <h4>Or drag and drop files below</h4>
                 <div class="row">
                 	<div class="col-lg-10">
                 		<div class="upload-drop-zone" id="drop-zone">
            			Just drag and drop files here
          				</div>
                 	</div>
                 </div>
          			
                <hr>
                <h4> Recently Added Documents </h4>
                <br/>
                
              <c:forEach items="${display.docs}" var="document">					
				
                <div class="row">
                    <div class="col-lg-10 ">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    
                                    <div class="col-xs-9 text-left">
                                        <div class="huge">Uploaded by ${document.docAuthor} </div>
                                        <div>Description: ${document.docName}</div>
                                    </div>
                                </div>
                            </div>
                            <a href="#">
                                <div class="panel-footer">
      
                                    <span class="pull-left" onclick="javascript:load_content('pnuts');">
                                    	<img class="thumb-nail" src=".${document.docUrl}" width ="720" height="420" />
									</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    
                </div>
             </c:forEach>
             
            </div>
            <hr>
            <div id = "content_div" hidden=true style="border:1px solid black;height:40%;overflow:auto">
                <p id="page_content"> 

                </p>
        <div style = " height:30%; overflow:auto">
                    <p id= "java" hidden=true>
                    Being a Java programmer and Software developer, I have learned a lot from articles titled as What Every Programmer Should Know about 鈥?. , they tend to give a lot of useful and in-depth information about a particular topic, which otherwise is very hard to discover. In my quest of learning I have come across some very useful articles, which I have bookmarked for reference and multiple reading. I personally think that all programmer can benefit by reading these articles, which makes me write this post and share all of these 鈥淲hat Every Programmer Should Know鈥?articles with you guys. These are from my personal bookmarks. In this article, you will see classic what every programmer should know article from topics like memory, Unicode, floating point arithmetic,networking, object oriented design, time, URL Encoding, String and many more. This list is very important for beginner and newcomers, as they are the ones, who lacks practical knowledge. Since most of these post are actually driven by practical knowledge, beginner and intermediate programmers can take a lot from it. Also gaining knowledge of fundamentals early in career helps to avoid mistakes, which has done by other programmers and software developers on their course of learning. Though it鈥檚 not easy to grasp all knowledge given in these articles in just one reading. You probably won鈥檛 understand some details about floating point number or get confused with subtle details of memory, but it鈥檚 important to keep these list handy and refer them time to time with a context. So Good luck and Enjoy reading these wonderful articles.  By the way, don鈥檛 forget to share any What Every Programmer Should know article, if it鈥檚 not already in this list.


                </p>
                <p id = "rails" hidden=true>
                    I鈥檝e been developing web applications for a while. I don鈥檛 often fall in love with the latest hot technology: there are always pros and cons, and always alternatives around the corner. The last time I found myself enamoured was when I discovered Rails several years ago. Rails was special in many ways, and it took the web development world by storm. Now I am beginning to get that same feeling for some new technologies: the AngularJS framework and the MEAN.js stack. In this article I will explore this new stack, and recreate the demo that put Rails on the map.

                    About 7 years ago, I had heard of Ruby on Rails, but not given it much thought. When I landed a gig that involved Rails work, I was prompted to watch the famous DHH blog demo鈥?I was blown away. I embraced this new world: I remember devouring a couple of the Prag books, even sneaking some chapters while away for a weekend with my wife. For me, the reason that Rails became so popular is simply this: the framework did a great job of making the common tasks of creating web applications simple and enjoyable. 鈥淐onvention over configuration,鈥?powerful code generators, and the beauty of the Ruby language made development fast and fun.

                    In the years since, there have been many frameworks and stacks doing very similar things to Rails. But nothing has knocked Rails of its perch in my mind, as the single most productive stack for typical web applications. But lately I think a sea change has started in the web application world: single-page design, reactive style programming, and NoSQL databases have become great fits for many newer web applications, and this provides an opening for new solutions. Friends and respected voices have started talking about AngularJS and the MEAN stack, and the increasing mindshare and momentum of these technologies is palpable. I am also personally very intrigued by the promise of sharing logic, specifically JavaScript code, on the client and the server. For all these reasons, I have decided to explore this stack as a potential successor to Rails.

                </p>
                <p id = "scala" hidden=true>
                    The popularization of the Scala programming language, noticeable by the abundance of opinions and criticism on blogs and social networks (like this one by Nikita Ivanov from GridGain and the popular Yammer case), greatly increased the amount of information about the language. However, the quality of such information often leaves much to be desired.

                    Whether those opinions are favorable or contrary to the Scala, they often contain outdated, superficial or biased statements. The goal of this article is to help those learning or evaluating Scala to come into their own conclusions. It presents the most common questions about language and its environment and, for each one, added clarifications, examples and links, favoring the formation of a better opinion or a more accurate assessment.

                    Scala is a compiled language, designed to run on a managed environment, most likely the JVM, and offers the union of the functional and object-oriented paradigms. Scala offers functional and object-oriented programming, a modern compiler and a type system checked at compile time, as in Java, but with the expressive syntax of (usually) interpreted languages, such as Groovy or Ruby. However, the same features that makes Scala expressive can also lead to performance problems and complexity. This article details where this balance needs to be considered and it is not an introduction or tutorial, but does not presume knowledge in Scala.

                </p>
                <p id = "hadoop" hidden=true>
                    Big data is a popular topic these days, not only in the tech media, but also among mainstream news outlets. And October's official release of big data software framework Hadoop 2.0 is generating even more media buzz.

                    But while you, InformationWeek reader, clearly understand Hadoop's significance, there's a high probability that many people in your organization -- including more than a few managerial types in the C-suite -- aren't really sure what Hadoop is, what it does, or why it's important.

                    So, how do you explain Hadoop to non-geeks? One approach is to focus on the benefits of Hadoop and big data, rather than providing mind-numbing details (with forgettable acronyms) on how it all works.

                    Forrester analyst Mike Gualtieri took this "benefits" approach in June when he posted a brief tutorial video that provided an easy-to-grasp overview of Hadoop. He calls it a platform that makes big data easier to manage.

                    [Here's why your business users may want to know more about Hadoop: Hadoop's Second Generation Offers More To Enterprises.]

                    "To understand Hadoop, you have to understand two fundamental things about it," Gualtieri explained in his video. They are: How Hadoop stores files, and how it processes data.

                    He added: "Imagine you had a file that was larger than your PC's capacity. You could not store that file, right? Hadoop lets you store files bigger than what can be stored on one particular node or server. So you can store very, very large files. It also lets you store many, many files."

                </p>
                <p id = "hive" hidden=true>
                    Apache Hive supports analysis of large datasets stored in Hadoop's HDFS and compatible file systems such as Amazon S3 filesystem. It provides an SQL-like language called HiveQL[6] with schema on read and transparently converts queries to map/reduce, Apache Tez[7] and in the future Spark[8] jobs. All three execution engines can run in Hadoop YARN. To accelerate queries, it provides indexes, including bitmap indexes.[9]

                    By default, Hive stores metadata in an embedded Apache Derby database, and other client/server databases like MySQL can optionally be used.[10]

                    Currently, there are four file formats supported in Hive, which are TEXTFILE,[11] SEQUENCEFILE, ORC[12] and RCFILE.[13][14][15] Apache Parquet can be read via plugin in versions later than 0.10 and natively starting at 0.13.[16][17]

                    Other features of Hive include:

                        Indexing to provide acceleration, index type including compaction and Bitmap index as of 0.10, more index types are planned.
                        Different storage types such as plain text, RCFile, HBase, ORC, and others.
                        Metadata storage in an RDBMS, significantly reducing the time to perform semantic checks during query execution.
                        Operating on compressed data stored into the Hadoop ecosystem using algorithms including DEFLATE, BWT, snappy, etc.
                        Built-in user defined functions (UDFs) to manipulate dates, strings, and other data-mining tools. Hive supports extending the UDF set to handle use-cases not supported by built-in functions.
                        SQL-like queries (HiveQL), which are implicitly converted into MapReduce or Tez jobs.


                </p>
                <p id = "pnuts" hidden=true>
                    (Please note, that鈥檚 the first and last time in this article that I鈥檒l be using the exclamation mark in Yahoo鈥檚 name, it looks funny.)

                    As you might expect from the company that runs Flickr, Yahoo have need for a large scale distributed data store. In particular, they need a system that runs in many geographical locations in order to optimise response times for users from any region, while at the same time coordinating data across the entire system. As ever, the system must exhibit high availability and fault tolerance, scalability and good latency properties.

                    These, of course, are not new or unique requirements. We鈥檝e seen already that Amazon鈥檚 Dynamo, and Google鈥檚 BigTable/GFS stack offer similar services. Any business that has a web-based product that requires storing and updating data for thousands of users has a need for a system like Dynamo. Many can鈥檛 afford the engineering time required to develop their own tuned solution, so settle for well-understood RDBMS-based stacks. However, as readers of this blog will know, RDBMSs can be almost too strict in terms of how data are managed, sacrificing responsiveness and throughput for correctness. This is a tradeoff that many systems are willing to explore.

                    PNUTS is Yahoo鈥檚 entry into this space. As usual, it occupies the grey areas somewhere between a straight-forward distributed hash-table and a fully-featured relational database. They published details in the conference on Very Large DataBases (VLDB) in 2008. Read on to find out what design decisions they made鈥?

                </p>
                <p id = "c" hidden=true>
                    C# is designed to be a platform-independent language in the tradition of Java (although it is implemented primarily on Windows). It's syntax is similar to C and C++ syntax, and C# is designed to be an object-oriented language. There are, for the most part, minor variations in syntax between C++ and C#. Main has no return type, there are no semicolons after class names, there are some (to C++ programmers) strange decisions regarding capitalization - such as the capitalization of Main. Other a few differences, the syntax is often the same. This decision is reasonable, in light of the fact that C syntax has been used with several other languages - notably Java.

                    Similar to Java, C# does not support multiple inheritance; instead it provides Java's solution: interfaces. Interfaces implemented by a class specify certain functions that the class is guaranteed to implement. Interfaces avoid the messy dangers of multiple inheritance while maintaining the ability to let several classes implement the same set of methods.

                    Another helpful feature of C# is garbage collection. Therefore, it is unnecessary to include a destructor for each class unless a class handles unmanaged resources; if so, it's necessary to release control those resources from within the class (The Finalize function is used to clear up these unmanaged resources; it can even be abbreviated with the same syntax as a C++ destructor). Of course, C# also provides direct access to memory through C++ style pointers, but these pointers are not garbage collected until specifically released by the programmer.

                    C#, as part of the .NET framework, is compiled to Microsoft Intermediate Language (MSIL), which is a language similar to Java's bytecode. MSIL allows C# to be platform independent and runs using just in time compiling. Therefore programs running under .NET gain speed with repeated use. Furthermore, because the other languages that make up the .NET platform (including VB and Cobol) compile to MSIL, it is possible for classes to be inherited across languages. The MSIL, like bytecode, is what allows C# to be platform independent.

                    The potential for C# is great if the .NET platform succeeds. C# is designed to take advantage of the design of .NET, and Microsoft has poured a great deal of money into .NET. Do you need to learn C#? If you know C++, you'll probably be able to pick it up quickly, and yes, you can still use C++ with .NET. It's important to keep an eye on C# to see how it will affect you.

                </p>
                <p id = "java_compiler" hidden=true>
                    Being a Java programmer and Software developer, I have learned a lot from articles titled as What Every Programmer Should Know about 鈥?. , they tend to give a lot of useful and in-depth information about a particular topic, which otherwise is very hard to discover. In my quest of learning I have come across some very useful articles, which I have bookmarked for reference and multiple reading. I personally think that all programmer can benefit by reading these articles, which makes me write this post and share all of these 鈥淲hat Every Programmer Should Know鈥?articles with you guys. These are from my personal bookmarks. In this article, you will see classic what every programmer should know article from topics like memory, Unicode, floating point arithmetic,networking, object oriented design, time, URL Encoding, String and many more. This list is very important for beginner and newcomers, as they are the ones, who lacks practical knowledge. Since most of these post are actually driven by practical knowledge, beginner and intermediate programmers can take a lot from it. Also gaining knowledge of fundamentals early in career helps to avoid mistakes, which has done by other programmers and software developers on their course of learning. Though it鈥檚 not easy to grasp all knowledge given in these articles in just one reading. You probably won鈥檛 understand some details about floating point number or get confused with subtle details of memory, but it鈥檚 important to keep these list handy and refer them time to time with a context. So Good luck and Enjoy reading these wonderful articles.  By the way, don鈥檛 forget to share any What Every Programmer Should know article, if it鈥檚 not already in this list.

                </p>
                <p id = "polyglot" hidden=true>
                    By now you have no doubt heard about polyglots and their amazing language abilities. Many of these self-titled language masters claim to have a simple solution or 鈥渢rick鈥?to mastering any given language in an amazingly short amount of time. But, is there truth to this claim, or is it well-rehearsed hyperbole? In order to assess the validity of these claims, let us look at what it means to be a master of something.

                    According to the Oxford English Dictionary, a 鈥渕aster鈥?is someone who has 鈥淎cquire[d] complete knowledge or skill in (an accomplishment, technique, or art).鈥?


                    Thus, a master is an expert, an individual who has truly mastered their chosen art. Yet, we can look beyond just definitions, since science allows us to quantify expertise! In 1993, Professor Anders Ericsson published a paper titled 鈥淭he Role of Deliberate Practice in the Acquisition of Expert Performance.鈥?Based off extensive research conducted over a number of years, Ericsson concluded that to achieve 鈥渆xpertise requires 10,000 hours鈥?of practice.

                </p>
                <p id = "ruby_on_rails" hidden=true>
                    I鈥檝e been developing web applications for a while. I don鈥檛 often fall in love with the latest hot technology: there are always pros and cons, and always alternatives around the corner. The last time I found myself enamoured was when I discovered Rails several years ago. Rails was special in many ways, and it took the web development world by storm. Now I am beginning to get that same feeling for some new technologies: the AngularJS framework and the MEAN.js stack. In this article I will explore this new stack, and recreate the demo that put Rails on the map.

                    About 7 years ago, I had heard of Ruby on Rails, but not given it much thought. When I landed a gig that involved Rails work, I was prompted to watch the famous DHH blog demo鈥?I was blown away. I embraced this new world: I remember devouring a couple of the Prag books, even sneaking some chapters while away for a weekend with my wife. For me, the reason that Rails became so popular is simply this: the framework did a great job of making the common tasks of creating web applications simple and enjoyable. 鈥淐onvention over configuration,鈥?powerful code generators, and the beauty of the Ruby language made development fast and fun.

                    In the years since, there have been many frameworks and stacks doing very similar things to Rails. But nothing has knocked Rails of its perch in my mind, as the single most productive stack for typical web applications. But lately I think a sea change has started in the web application world: single-page design, reactive style programming, and NoSQL databases have become great fits for many newer web applications, and this provides an opening for new solutions. Friends and respected voices have started talking about AngularJS and the MEAN stack, and the increasing mindshare and momentum of these technologies is palpable. I am also personally very intrigued by the promise of sharing logic, specifically JavaScript code, on the client and the server. For all these reasons, I have decided to explore this stack as a potential successor to Rails.

                </p>
        </div> <!-- div for p -->
    </div>
    <!-- /#page-content-wrapper -->

    
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