<?php
       
        $con = mysqli_connect("###HOST###","###USER###","###PASSWORD###","###DATABASE###") or die('Unable to Connect');
        $userid = $_GET['userid'];
	
	    $password = $_GET['password'];
		if($userid == '' || $password == '' )
		{
	
		echo 'ERROR';
		}
		else{
		$sql = "SELECT * FROM users WHERE userid='$userid' AND password='$password'";
	        $check = mysqli_fetch_array(mysqli_query($con,$sql));
		if(isset($check)){
		echo 'FOUND';
		}
		else{ 
			echo 'NOT_FOUND';
		 
        }
	        
	   }		
		?>
