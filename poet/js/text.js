var imgUrl = [
			// display: block; width: 1226px; height: 460px;
			'https://th.bing.com/th/id/R080fe1f489f97ac0381b821ffb905832?rik=fivXnfqVtXn5Ww&pid=ImgRaw',	
			'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/676ddccc418842904f82ccdc66bbaa34.jpg?w=2452&h=920',
			'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/cf6ba4d372b80e939104cf369f14139a.jpg?thumb=1&w=1226&h=460&f=webp&q=90',
			'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/9ebff5f5c1f52f2dbdd611897adbefd4.jpg?thumb=1&w=1226&h=460&f=webp&q=90',
			'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/0ef4160c861b998239bce9adb82341e7.jpg?thumb=1&w=1226&h=460&f=webp&q=90',
			'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/0fbb6a386c8a1c0acb7d7ab75d0ffa88.jpg?thumb=1&w=1226&h=460&f=webp&q=90'
		];

		var imgs = document.querySelectorAll('.swiper-lazy');

		for (var i = 0; i < imgs.length; i++) {
			imgs[i].src = imgUrl[i];
		}