<?php
$connect = mysqli_connect("localhost","root","","quanlychungcu");
mysqli_query($connect, "SET NAMES 'utf8'");

$query = "SELECT * FROM user";
$data = mysqli_query($connect, $query);
class User{
	function User($id_apartment, $id_user, $name, $email, $phone_number, $gender, $address, $birthday, $avatar){
		$this->Id_apartment = $id_apartment;
		$this->Id_user = $id_user;
		$this->Name = $name;
		$this->Email = $email;
		$this->Phone_number = $phone_number;
		$this->Gender = $gender;
		$this->Address = $address;
		$this->Birthday = $birthday;
		$this->Avatar = $avatar;
	}
}

$mangUser = array();
while($row = mysqli_fetch_assoc($data)){
	array_push($mangUser, new User($row['id_apartment'], $row['id_user'], $row['name'], $row['email'], $row['phone_number'],$row['gender'],$row['address'],$row['birthday'],$row['avatar']));	
}
echo json_encode($mangUser);
?>
