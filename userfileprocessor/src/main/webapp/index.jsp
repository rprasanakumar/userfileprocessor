<!DOCTYPE HTML>
<html>
	<head>
		<title>User File Processor</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="resources/css/main.css" />
  
    
    <title>User File Processor
    </title>
	<script>
	

	
	
	
	function getColor(){
		var urlColor = "webapi/user/color";
		var client = new XMLHttpRequest();
		client.open("GET", urlColor, false);

		client.setRequestHeader("Content-Type", "application/json");

		client.send();

		if (client.status == 200){
			document.getElementById("response").value = client.responseText;
			
		}
		    
		   
	}
	
	
	function getColorNname(){
		
		var urlColor = "webapi/user/name";
		var client = new XMLHttpRequest();

		client.open("GET", urlColor, false);

		client.setRequestHeader("Content-Type", "application/json");

		client.send();

		if (client.status == 200){
			document.getElementById("response").value = client.responseText;
			
		}
		    
		   
	}
	
	
	function getVenues(){
		var query = document.getElementById("query").value;
		if(query==""){
			query ="all"
		}
		var urlVenue = "webapi/user/venue/"+query;
		var client = new XMLHttpRequest();

		client.open("GET", urlVenue, false);

		client.setRequestHeader("Content-Type", "application/json");

		client.send();

		if (client.status == 200){
			document.getElementById("response").value = client.responseText;
			
		}
		    
		   
	}
	
	
/* 	function putFile(){
		alert("Inside");
		var url = "webapi/user/file/";
		var client = new XMLHttpRequest();
		var file = document.getElementById("file").value;
		client.open("POST", url, false);

		client.setRequestHeader("Content-Type", "multipart/form-data");

		client.send(file);

		if (client.status == 200){
			document.getElementById("response").value = client.responseText;
			
		} */
		    
		   
	}

</script>
  
   <style>
  	.demo1 { 
  	font-size: 100px;
  	font-family: "Century Gothic", "Helvetica", sans-serif;
    font-weight: bold;
    font-weight: italic;
    text-align: center;
    color: #333;
    background-color: #666;
    text-shadow: 0px 1px 0px rgba(255,255,255,.5); /* 50% white from bottom */
}
   </style>
    </head>
<body>
		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<header id="header">
				
  <div class="container">
    <div class="demo1">
          <a href="#"> User File Processor</a>
    </div>   
</div>
		</header>


				<!-- Main -->
<section id="main">


		<div id="wrapper">
			<font color="red">${displayContent}</font>
			<br>
			Select a file to Upload<br><br>
			
		<form name="groupUpload" method ="post" action="webapi/user/file/" enctype="multipart/form-data" >
	
		    <input type='file' name='file'><br><br>
		    <input type='submit' name='upload_btn' value='upload' >
		</form>
		</div>


<!-- <input placeholder="Response" type="textarea" id="response" name="response" readonly/> -->

<textarea id="response" cols="10" readonly> 
</textarea>

<br>

  <form accept-charset="UTF-8" action="javascript:void(0);" method="post">
      

       	 <button  type="submit" class="yj-btn" onclick="getColor()" ><span>Get Color Count</span></button>
		 <button  type="submit" class="yj-btn" onclick="getColorNname()" ><span>Get Color Count Name</span></button>
		 <br><br>
		 <span>  <input placeholder="Query" aria-required="true" id="query" name="query" size="30" type="text" /></span><br>
        <button  type="submit" class="yj-btn" onclick="getVenues()" ><span>Get Venues</span></button>

  </form>

</section>


</div>

			



	</body>
</html>