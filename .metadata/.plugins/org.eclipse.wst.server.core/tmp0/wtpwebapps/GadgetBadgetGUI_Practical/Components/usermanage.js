/**
 * 
 */

/**
 * 
 */
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hiduserIDSave").val("");
	$("#USERMANAGE")[0].reset();
});


$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#usermanagesaveid").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "UserAPI",
		type : type,
		data : $("#USERMANAGE").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});

});




function onItemSaveComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#usersGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error") {
		
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#hiduserIDSave").val("");
	$("#USERMANAGE")[0].reset();
}


$(document).on("click", ".btnRemove", function(event) {
	
	$.ajax({
		url : "UserAPI",
		type : "DELETE",
		data : "iduser=" + $(this).data("iduser"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});


function onItemDeleteComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#usermanageGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error") {
		
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}


// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event)
		{
			$("#hiduserIDSave").val($(this).data("iduser"));
			$("#username").val($(this).closest("tr").find('td:eq(0)').text());
			$("#email").val($(this).closest("tr").find('td:eq(1)').text());
			$("#dob").val($(this).closest("tr").find('td:eq(2)').text());
			$("#password").val($(this).closest("tr").find('td:eq(3)').text());
				
		});
		
// CLIENTMODEL=========================================================================
function validateItemForm() {
	
	
	if ($("#username").val().trim() == "") {
		return "Please insert Username.";
	}
	
	// username
	if ($("#email").val().trim() == "") {
		return "Please insert Email.";
	}
	
	// type
	if ($("#dob").val().trim() == "") {
		return "Please insert date of birth.";
	}

	// password
	if ($("#password").val().trim() == "") {
		return "Please insert Valid Password.";
	}
	
	
	
	return true;
}


