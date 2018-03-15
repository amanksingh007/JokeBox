<?php

$conn = new mysqli($servername, $username, $password, $database);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
$ALL_POSTS = array(); 
$sql = "SELECT userid,post FROM POSTS order by posttime desc;";
$stmt = $conn->prepare($sql);
$stmt->execute();
$stmt->bind_result($id, $post);
while($stmt->fetch()){
	$temp = [
		'id'=>$id,
		'post'=>$post
	];
	array_push($ALL_POSTS, $temp);
}

echo json_encode($ALL_POSTS);
?>
