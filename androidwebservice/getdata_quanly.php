<?php
$connect = mysqli_connect("localhost","root","","quanlychungcu");
mysqli_query($connect, "SET NAMES 'utf8'");

$query = "SELECT * FROM accountquanly";
$data = mysqli_query($connect, $query);
class User{
	function User($id, $username, $password){
		$this->Id = $id;
		$this->Username = $username;
		$this->Password = $password;
	}
}

$mangUser = array();
while($row = mysqli_fetch_assoc($data)){
	array_push($mangUser, new User($row['id'], $row['user_name'], $row['password']));	
}
echo json_encode($mangUser);
?>
