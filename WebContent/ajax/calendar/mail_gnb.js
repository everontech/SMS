// 탑메뉴

function tm_on(id) {
	if (id == b_id) return false;

	// 선택된메뉴 비활성화
	if (b_id) {
		var b_otm = document.getElementById("tm_"+b_id);
		var b_otma = document.getElementById("tm_"+b_id+"_a");
		var b_osm = document.getElementById("s_tm_"+b_id);
		if (b_id != "mail") {
			b_otm.className = "tmnu";
		}
		b_otma.className = "m01";
		b_osm.style.visibility = "hidden";

		// 오른쪽메뉴 Bar 활성화
		var b_idr = get_idr(b_id);
		if (b_idr) {
			var b_otmr = document.getElementById("tm_"+b_idr);
			b_otmr.className = "tmnu";
		}
	}

	// 현재메뉴 활성화
	var otm = document.getElementById("tm_"+id);
	var otma = document.getElementById("tm_"+id+"_a");
	var osm = document.getElementById("s_tm_"+id);
	otm.className = "tmnu_on";
	otma.className = "m01 on";
	osm.style.visibility = "visible";

	// 오른쪽메뉴 Bar 비활성화
	var idr = get_idr(id);
	if (idr) {
		var otmr = document.getElementById("tm_"+idr);
		otmr.className = "tmnu_on";
	}

	// 선택된메뉴 대체
	b_id = id;
}

function get_idr(id) {
	switch (id) {
		case "mail":
			return "message";
		case "message":
			return "mileage";
		case "mileage":
			return "premium";
		case "premium":
			return "team";
		case "team":
			return "note";
		case "note":
			return "address";

		default:
			return false;
	}
}