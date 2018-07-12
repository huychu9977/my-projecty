$(document)
		.ready(
				function() {
					$('#modal1').modal();
					var table = $('#example')
							.DataTable(
									{
										"bProcessing" : true,
										"bServerSide" : true,
										"sort" : "position",
										// bStateSave variable you can use to
										// save state on client cookies: set
										// value "true"
										"bStateSave" : true,
										// Default: Page display length
										"iDisplayLength" : 10,
										// We will use below variable to track
										// page number on server side(For more
										// information visit:
										// http://legacy.datatables.net/usage/options#iDisplayStart)
										"iDisplayStart" : 0,
										"fnDrawCallback" : function() {
											// Get page numer on client. Please
											// note: number start from 0 So
											// for the first page you will see 0
											// second page 1 third page 2...
											// Un-comment below alert to see
											// page number
											// alert("Current page number:
											// "+this.fnPagingInfo().iPage);
										},
										"sAjaxSource" : "boot/students",
										"aoColumns" : [

												{
													"mData" : "name",
													"className": "text-center"
												},
												{
													"mData" : "phone"
												},
												{
													"mData" : "address"
												},
												{
													"mData" : "birthday"
												},

												{
													"mData" : "id",
													render : function(data,
															type) {
														// console.log(data);
														return '<button id-student="'
																+ data
																+ '" data-target="modal1" style="margin-right:5px;" class="waves-effect waves-light btn modal-trigger btn-small edit"><i class="material-icons ">border_color</i></button>'
																+ '<button id-student="'
																+ data
																+ '" class="waves-effect waves-light btn-small red delete"><i class="material-icons ">delete</i></button>';

													}
												} ]
									});
					$('#example').on('click', '.edit', function() {
						var id = $(this).attr('id-student');
						$.ajax({
							url : 'boot/students/' + id,
							type : 'get',
							success : function(data) {
								$('#id').removeClass('hidden');
								$('input[name="id"]').val(data.id);
								$('#name').val(data.name);
								$('#phone').val(data.phone);
								$('#address').val(data.address);
								$('#birthday').val(data.birthday);
								M.updateTextFields();
							}
						})
					})
					$('#example')
							.on(
									'click',
									'.delete',
									function() {
										var $this = $(this);
										swal(
												{
													title : "Are you sure?",
													text : "You will not be able to recover this record!",
													type : "warning",
													showCancelButton : true,
													confirmButtonColor : "#DD6B55",
													confirmButtonText : "Yes, delete it!",
													cancelButtonText : "No, cancel plx!",
													closeOnConfirm : false,
													closeOnCancel : true
												},
												function(isConfirm) {
													if (isConfirm) {
														var id = $this
																.attr('id-student');
														$
																.ajax({
																	url : 'boot/students/'
																			+ id,
																	type : 'delete',
																	success : function(
																			data) {
																		if (data == true) {
																			swal(
																					"Deleted!",
																					"Your record has been deleted.",
																					"success");
																			table.ajax
																					.reload();
																		}
																	}
																})
													}

												});

									})
					$('.add').click(function() {

						$('#id').addClass('hidden');
						$('input[name="name"]').val('');
						$('input[name="phone"]').val('');
						$('input[name="address"]').val('');
						$('input[name="birthday"]').val('');
					})
					$('#formValidate').validate({
						rules : {
							name : {
								required : true,
								minlength : 5
							},
							address : {
								required : true
							},
							phone : {
								required : true,
								minlength : 10
							},
							birthday : {
								required : true
							}
						},
						// For custom messages
						messages : {
							name : {
								required : "Enter a name",
								minlength : "Enter at least 5 characters"
							},
							phone : {
								required : "Enter a phone",
								minlength : "Enter at least 10 characters"
							}
						},
						errorElement : 'div',
						errorPlacement : function(error, element) {
							var placement = $(element).data('error');
							if (placement) {
								$(placement).append(error)
							} else {
								error.insertAfter(element);
							}
						}
					});
					$('#formValidate')
							.submit(
									function(e) {
										if ($(this).valid()) {

											e.preventDefault();
											var type;
											var id;
											if ($('#id').hasClass('hidden')) {
												type = 'post';
												id = '';
											} else {
												type = 'put';
												id = '/'
														+ $('input[name="id"]')
																.val();
											}

											$
													.ajax({
														url : 'boot/students'
																+ id,
														type : type,
														dataType : 'json',
														contentType : "application/json;charset=UTF-8",
														data : JSON
																.stringify({
																	"name" : $(
																			'input[name="name"]')
																			.val(),
																	"phone" : $(
																			'input[name="phone"]')
																			.val(),
																	"address" : $(
																			'input[name="address"]')
																			.val(),
																	"birthday" : $(
																			'input[name="birthday"]')
																			.val(),
																}),
														success : function(data) {
															console.log(data)
															if (data == true) {
																$('#modal1')
																		.modal(
																				'close');
																table.ajax
																		.reload();
																M
																		.toast({
																			html : 'Success!'
																		})
															}
														}
													})
										}
									})
				})