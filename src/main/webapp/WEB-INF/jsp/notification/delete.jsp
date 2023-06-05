<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>
function deleteNotifications() {
  $.ajax({
    url: '/notification/delete',
    type: 'POST',
    success: function(data) {
      console.log("Delete completed");
      // Reload notifications after successful delete
      pollForNotifications();
    },
    error: function(jqXHR, textStatus, errorThrown) {
      // Handle error here
      console.error("deleteNotifications Error occurred: ", textStatus, errorThrown);
    }
  });
}
</script>