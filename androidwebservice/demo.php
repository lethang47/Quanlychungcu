<?php
class SinhVien{
	function SinhVien($hoten, $namsinh, $diachi){
		$this->HoTen = $hoten;
		$this->NamSinh = $namsinh;
		$this->DiaChi = $diachi;
	}
}
$mangSV = array();

array_push($mangSV, new SinhVien("Thang", 2000, "Hanoi"));
array_push($mangSV, new SinhVien("Tung", 2001, "Hanoi"));

echo json_encode($mangSV);
?>
