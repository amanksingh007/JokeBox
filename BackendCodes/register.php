
<?php
        $con = mysqli_connect("#######","######","######","mydatabase") or die('Unable to Connect');
        $userid = $_GET['userid'];
	
	    $password = $_GET['password'];
	    if(strlen($password)<5){
	        echo "password is very small";
	    }
	    else{
	
	    $email = $_GET['useremail'];
		if($userid == '' || $password == '' || $email == ''){
	        echo 'please fill all values'.$userid.'xxx'.$email.'xxx'.$password;
		}
		else{
		    $sql = "SELECT * FROM users WHERE userid='$userid' OR emailid='$email'";
	        $check = mysqli_fetch_array(mysqli_query($con,$sql));
		    if(isset($check)){
		        echo 'username or email already exist';
		    }
		    else{
		        $sql = "INSERT INTO users (userid,emailid,password) VALUES('$userid','$email','$password')";
		        if(mysqli_query($con,$sql)){
		        	echo 'successfully registered';
                }
		        else{
				    echo 'oops! Please try again!';
	            }
            }
	        
	   }	
	}
?>
