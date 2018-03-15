
<?php
        $con = mysqli_connect("HOST","id26064","12345678901224","DATABASE") or die('Unable to Connect');
        $userid = $_GET['userid'];
	    $password = $_GET['password'];
	    $post=$_GET['post'];
	    $post=str_replace("^"," ",$post);
		if($userid == '' || $password == '' )
		{
	
		echo 'ERROR';
		}
		else{
		$sql = "INSERT INTO POSTS (userid,post) values('$userid','$post');";
	    if ($con->query($sql) === TRUE)
             echo "New record created successfully";
        else
            echo "Error: " . $sql . "<br>" . $conn->error;
		}
		?>
