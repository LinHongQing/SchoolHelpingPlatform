(function($) {
	$.getUrlParam = function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
})(jQuery);

(function($) {
	$.extend({
		myTime: {
			/**
			 * 当前时间戳
			 * @return <int>    unix时间戳(秒) 
			 */
			CurTime : function() {
				return Date.parse(new Date()) / 1000;
			},
			/**       
			 * 日期 转换为 Unix时间戳
			 * @param <string> 2014-01-01 20:20:20 日期格式       
			 * @return <int>    unix时间戳(秒)       
			 */
			DateToUnix : function(string) {
				var f = string.split(' ', 2);
				var d = (f[0] ? f[0] : '').split('-', 3);
				var t = (f[1] ? f[1] : '').split(':', 3);
				return (new Date(parseInt(d[0], 10) || null, (parseInt(
					d[1], 10) || 1) - 1,
					parseInt(d[2], 10) || null, parseInt(t[0], 10)
							|| null, parseInt(t[1], 10) || null,
					parseInt(t[2], 10) || null)).getTime() / 1000;
			},
			/**       
			 * 时间戳转换日期       
			 * @param <int> unixTime  待时间戳(秒)       
			 * @param <bool> isFull  返回完整时间(Y-m-d 或者 Y-m-d H:i:s)       
			 * @param <int> timeZone  时区       
			 */
			UnixToDate : function(unixTime, isFull, timeZone) {
				if (typeof (timeZone) == 'number') {
					unixTime = parseInt(unixTime) + parseInt(timeZone) * 60 * 60;
				}
				var time = new Date(unixTime * 1000);
				var ymdhis = "";
				ymdhis += time.getUTCFullYear() + "-";
				if (time.getUTCMonth() < 10)
					ymdhis += "0";
				ymdhis += (time.getUTCMonth() + 1) + "-";
				if (time.getUTCDate() < 10)
					ymdhis += "0";
				ymdhis += time.getUTCDate();
				if (isFull == true) {
					ymdhis += " ";
					if (time.getUTCHours() < 10)
						ymdhis += "0";
					ymdhis += time.getUTCHours() + ":";
					if (time.getUTCMinutes() < 10)
						ymdhis += "0";
					ymdhis += time.getUTCMinutes() + ":";
					if (time.getUTCSeconds() < 10)
						ymdhis += "0";
					ymdhis += time.getUTCSeconds();
				}
				return ymdhis;
			}
		}
	});
})(jQuery);

//Ajax 文件下载
(function($){    // 获得url和data
	$.download = function(url, data, method) {
		if ( url && data ){ 
			// data 是 string 或者 array/object
			data = typeof data == 'string' ? data : jQuery.param(data);        // 把参数组装成 form的  input
			var inputs = '';
			jQuery.each(data.split('&'), function() { 
				var pair = this.split('=');
				inputs += '<input type="hidden" name="' + pair[0] + '" value="' + pair[1] + '" />'; 
			});        // request发送请求
			jQuery('<form action="'+ url + '" method="' + (method||'post') + '">' + inputs + '</form>')
				.appendTo('body').submit().remove();
		}
	};
})(jQuery);

function unixTimestamptoDateString(unix) {
	return $.myTime.UnixToDate(unix, true, 8);
}

function dateStringtoUnixTimestamp(date) {
	return $.myTime.DateToUnix(date);
}