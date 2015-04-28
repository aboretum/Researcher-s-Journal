<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Researcher's Journal</title>


 <!-- Bootstrap Core CSS -->
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
    
    
</head>
<body>
		<h1>RESEARCHER'S JOURNAL</h1>
	    <div class="container">    
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-primary" >
                    <div class="panel-heading">
                        <div class="panel-title">Sign In</div>
                        <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#" onClick="$('#loginbox').hide(); $('#findPassWordbox').show()"><span style="color:#fffaf0;">Forgot password?</span></a></div>
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >

                        
                            
                        <form id="loginform" class="form-horizontal" role="form" action = "Login" method=POST>
                                    
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="username">                                        
                                    </div>
                                
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="login-password" type="password" class="form-control" name="password" placeholder="password">
                                    </div>
                                    
							<div >
                        	 <p style="color:#b94a48;">${info}</p>
                       		 </div>
                                
                            <div class="input-group">
                                      <div class="checkbox">
                                        <label>
                                          <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
                                        </label>
                                      </div>
                                    </div>


                                <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->

                                    <div class="col-sm-12 controls">
                                      <button id="btn-login" type="submit" class="btn btn-primary">Login  </button>
                                     
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="col-md-12 control">
                                        <div style="border-top: 1px solid#888; padding-top:15px; font-size:95%" >
                                            Don't have an account? 
                                        <a href="#" onClick="$('#loginbox').hide(); $('#signupbox').show()">
                                            Sign Up Here
                                        </a>
                                        </div>
                                    </div>
                                </div>  
                                
                                 <div class="form-group">
                                    <div class="col-md-12 control">
                                        <div style="border-top: 1px solid#888; padding-top:15px; font-size:95%" >
                                            Principle Investigator? 
                                        <a href="#" onClick="$('#loginbox').hide(); $('#groupsignupbox').show()">
                                            Register a group for your research team.
                                        </a>
                                        </div>
                                    </div>
                                </div>  
                            </form>     

                        </div>                     
                    </div>  
        </div>
        
        <div id="signupbox" style="display:none; margin-top:50px" class="mainbox col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="panel-title">Sign Up</div>
                            <div style="float:right; font-size: 85%; position: relative; top:-10px"><a id="signinlink" href="#" onclick="$('#signupbox').hide(); $('#loginbox').show()"><span style="color:#fffaf0;">Sign in</span></a></div>
                        </div>  
                        <div class="panel-body" >
                            <form id="signupform" class="form-horizontal" role="form" action="AddUser" method=POST>
                                
                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>Error:</p>
                                    <span></span>
                                </div>
                                    
                                <div class="form-group">
                                    <label for="email" class="col-md-3 control-label">Email</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="email" placeholder="Email Address">
                                    </div>
                                </div>
                                    
                                <div class="form-group">
                                    <label for="firstname" class="col-md-3 control-label">User Name</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="username" placeholder="User Name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="lastname" class="col-md-3 control-label">Password</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="password" placeholder="Password">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="col-md-3 control-label">Repeat Password</label>
                                    <div class="col-md-9">
                                        <input type="password" class="form-control" name="confirmPassword" placeholder="Repeat Password">
                                    </div>
                                </div>
                                    
                                <div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Title</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="member_title" placeholder="Title in team">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Group Name</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="groupName" placeholder="Your Group Name">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Group Password</label>
                                    <div class="col-md-9">
                                        <input type="password" class="form-control" name="groupKey" placeholder="Group Key">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <!-- Button -->                                        
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="btn-signup" type="submit" class="btn btn-info"><i class="icon-hand-right"></i> Sign Up</button>
                                        
                                    </div>
                                </div>
                            </form>
                         </div>
                    </div>
         </div> 
         
         <div id="groupsignupbox" style="display:none; margin-top:50px" class="mainbox col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="panel-title">Group Sign Up</div>
                            <div style="float:right; font-size: 85%; position: relative; top:-10px"><a id="signinlink" href="#" onclick="$('#groupsignupbox').hide(); $('#loginbox').show()"><span style="color:#fffaf0;">Sign in</span></a></div>
                        </div>  
                        <div class="panel-body" >
                            <form id="signupform" class="form-horizontal" role="form" action="AddGroup" method=POST>
                                
                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>Error:</p>
                                    <span></span>
                                </div>
                                    
                                <div class="form-group">
                                    <label for="GName" class="col-md-3 control-label">Group Name</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="group_name" placeholder="Dr.King's Group">
                                    </div>
                                </div>
                                    
                                <div class="form-group">
                                    <label for="Password" class="col-md-3 control-label">Group Key</label>
                                    <div class="col-md-9">
                                        <input type="password" class="form-control" name="group_key" placeholder="Choose a code">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="col-md-3 control-label">Repeat Key</label>
                                    <div class="col-md-9">
                                        <input type="password" class="form-control" name="confirm_group_key" placeholder="Repeat the code">
                                    </div>
                                </div>
                                    
                                <div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Field of Study</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="field_study" placeholder="Area">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <!-- Button -->                                        
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="btn-signup" type="submit" class="btn btn-info"><i class="icon-hand-right"></i> Register Group</button>
                                        
                                    </div>
                                </div>
                            </form>
                         </div>
                    </div>
         </div> 
         
         <div id="findPassWordbox" style="display:none; margin-top:50px" class="mainbox col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="panel-title">Retrieve Password</div>
                            <div style="float:right; font-size: 85%; position: relative; top:-10px"><a id="signinlink" href="#" onclick="$('#findPassWordbox').hide(); $('#loginbox').show()"><span style="color:#fffaf0;">Sign in</span></a></div>
                        </div>  
                        <div class="panel-body" >
                            <form id="signupform" class="form-horizontal" role="form" action="SendPassWord" method=POST>
                                
                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>${info}</p>
                                    <span></span>
                                </div>
                                    
                                <div class="form-group">
                                    <label for="Email" class="col-md-3 control-label">Email</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="email" placeholder="John@mail.com">
                                    </div>
                                </div>
                                    
                                <div class="form-group">
                                    <label for="Password" class="col-md-3 control-label">User Name</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="username" placeholder="UserName">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                	<div class="col-md-12 control">
                                	<div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                                           Your new password will be sent to your email address.
                                     </div>
                                     </div>
                                </div>
                                
                                <div class="form-group">
                                	
                                    <!-- Button -->                                        
                                    <div class="col-md-12 control">
                                        <button id="btn-signup" type="submit" class="btn btn-info"><i class="icon-hand-right"></i>Send it</button>
                                        
                                    </div>
                                   
                                </div>
                            </form>
                         </div>
                    </div>
         </div> 
         
         <div id="well1" style="margin-top:50px; font-size:18px;" class="mainbox col-md-4">
        	<div >
        	<p  class =" well text-justify">Researcher's Journal is a website dedicated to help researchers organize their daily research materials and findings. At the same time, the data can be shared within the researcher's group. 
        	</p>
        	<p class ="well text-justify">Note: To obtain an account, first ask your principle investigator to register a group and sign up for yourself using the group secret key.</p>
        	</div>
        </div>
    </div>
    
    
    <script type="text/javascript" src="./resources/js/jquery-1.8.3.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="./resources/js/bootstrap.min.js"></script>
</body>
</html>