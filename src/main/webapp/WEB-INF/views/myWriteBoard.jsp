<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<link href="./css/myBoard.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" charset="UTF-8"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
</head>
	<div class="card-body">
		<!-- Nav tabs -->
		<ul class="nav nav-pills nav-customs nav-danger"
			role="tablist">
			<li class="nav-item"><a class="nav-link active"
				data-bs-toggle="tab" href="#border-navs-home" role="tab">Home</a>
			</li>
			<li class="nav-item"><a class="nav-link" data-bs-toggle="tab"
				href="#border-navs-profile" role="tab">Profile</a></li>
			<li class="nav-item"><a class="nav-link" data-bs-toggle="tab"
				href="#border-navs-messages" role="tab">Messages</a></li>
			<li class="nav-item"><a class="nav-link" data-bs-toggle="tab"
				href="#border-navs-settings" role="tab">Settings</a></li>
		</ul>
		<div class="card mb-0">
			<!-- Tab panes -->
			<div class="tab-content text-muted">
				<div class="tab-pane active" id="border-navs-home" role="tabpanel">
					<div class="d-flex">
						<div class="flex-shrink-0">
							<i class="ri-checkbox-circle-fill text-success"></i>
						</div>
						<div class="flex-grow-1 ms-2">Raw denim you probably
							haven't heard of them jean shorts Austin. Nesciunt tofu
							stumptown aliqua, retro synth master cleanse.</div>
					</div>
					<div class="d-flex mt-2">
						<div class="flex-shrink-0">
							<i class="ri-checkbox-circle-fill text-success"></i>
						</div>
						<div class="flex-grow-1 ms-2">Too much or too little
							spacing, as in the example below, can make things unpleasant
							for the reader. The goal is to make your text as comfortable to
							read as possible.</div>
					</div>
				</div>
				<div class="tab-pane" id="border-navs-profile" role="tabpanel">
					<div class="d-flex">
						<div class="flex-shrink-0">
							<i class="ri-checkbox-circle-fill text-success"></i>
						</div>
						<div class="flex-grow-1 ms-2">In some designs, you might
							adjust your tracking to create a certain artistic effect. It
							can also help you fix fonts that are poorly spaced to begin
							with.</div>
					</div>
					<div class="d-flex mt-2">
						<div class="flex-shrink-0">
							<i class="ri-checkbox-circle-fill text-success"></i>
						</div>
						<div class="flex-grow-1 ms-2">A wonderful serenity has
							taken possession of my entire soul, like these sweet mornings
							of spring which I enjoy with my whole heart.</div>
					</div>
				</div>
				<div class="tab-pane" id="border-navs-messages" role="tabpanel">
					<div class="d-flex">
						<div class="flex-shrink-0">
							<i class="ri-checkbox-circle-fill text-success"></i>
						</div>
						<div class="flex-grow-1 ms-2">Each design is a new, unique
							piece of art birthed into this world, and while you have the
							opportunity to be creative and make your own style choices.</div>
					</div>
					<div class="d-flex mt-2">
						<div class="flex-shrink-0">
							<i class="ri-checkbox-circle-fill text-success"></i>
						</div>
						<div class="flex-grow-1 ms-2">For that very reason, I went
							on a quest and spoke to many different professional graphic
							designers and asked them what graphic design tips they live.</div>
					</div>
				</div>
				<div class="tab-pane" id="border-navs-settings" role="tabpanel">
					<div class="d-flex mt-2">
						<div class="flex-shrink-0">
							<i class="ri-checkbox-circle-fill text-success"></i>
						</div>
						<div class="flex-grow-1 ms-2">For that very reason, I went
							on a quest and spoke to many different professional graphic
							designers and asked them what graphic design tips they live.</div>
					</div>
					<div class="d-flex mt-2">
						<div class="flex-shrink-0">
							<i class="ri-checkbox-circle-fill text-success"></i>
						</div>
						<div class="flex-grow-1 ms-2">After gathering lots of
							different opinions and graphic design basics, I came up with a
							list of 30 graphic design tips that you can start implementing.
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>