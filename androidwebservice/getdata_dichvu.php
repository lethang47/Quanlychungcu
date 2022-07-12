<?php
$connect = mysqli_connect("localhost","root","","quanlychungcu");
mysqli_query($connect, "SET NAMES 'utf8'");

$query = "SELECT * FROM service";
$data = mysqli_query($connect, $query);
class Service{
	function Service($id, $name, $price_by_month, $registration_date, $term, $discount, $id_user) {
		$this->Id = $id;
		$this->Name = $name;
		$this->Price_by_month = $price_by_month;
		$this->Registration_date = $registration_date;
		$this->Term = $term;
		$this->Discount = $discount;
		$this->Id_user = $id_user;
	}
}
$mangUser = array();
while($row = mysqli_fetch_assoc($data)){
	array_push($mangUser, new Service($row['id'], $row['name'], $row['price_by_month'], $row['registration_date'], $row['term'],$row['discount'],$row['id_user']));	
}
echo json_encode($mangUser);

?>