<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "hauthenticationdb";
$name = $_POST['username'];
$phoneno = $_POST['phoneno'];

$conn = new mysqli($servername, $username, $password, $dbname);

if($conn->connect_error)
{
	$response = "connect not build";
}

	$sql = "insert into autthentication(Name,Phoneno) values($name,$phoneno)";
	
	if($conn->query($sql)===TRUE)
	{
		$response = "insert";
	}
	else
	{
		$response = "Not Inserted";
	}

	echo json_encode(array('response')=>response);
$conn->close();

?>