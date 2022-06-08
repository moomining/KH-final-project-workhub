var socket = null;

$(document).ready(function(){
	
	socket = new SockJS('/alert');
	
	socket.onopen = function() {
		console.log('open');
	};
	
	socket.onmessage = function(evt) {
		var data = evt.data;
		
		const Toast = Swal.mixin({
			toast: true,
			position: 'top-middle',
			showConfirmButton: false,
			timer: 3000,
			timerProgressBar: true,
			didOpen: (toast) => {
				toast.addEventListener('mouseenter', Swal.stopTimer);
				toast.addEventListener('mouseleave', Swal.resumeTimer);
			}
		})
		
		Toast.fire({
			icon: 'warning',
			html: data,
		})
	};
	
	socket.onclose = function() {
		console.log('close');
	}
	
});