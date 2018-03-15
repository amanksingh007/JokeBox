<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
     $image = $_POST['image'];
     $userid=$_POST['userid'];
      $con = mysqli_connect("HOST","id","0123411256789","id") or die('Unable to Connect');
 
 
    //$sql = "INSERT INTO users (image) VALUES (?) where userid='$userid';";
    $sql="UPDATE users set profilepic=? where userid='$userid';";
    $stmt = mysqli_prepare($con,$sql);
    
    mysqli_stmt_bind_param($stmt,"s",$image);
    mysqli_stmt_execute($stmt);
   
    $check = mysqli_stmt_affected_rows($stmt);
 
    if($check == 1)
         echo "Image Uploaded Successfully";
    else
         echo "Error Uploading .....Image".$stmt;
    mysqli_close($con);
 }
 else{
    echo "Error";
 }
 ?>
