<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type = "text/javascript">

$(function() {
	$('.more-btn').on('click', function() {
		if ($(this).children().is('.open')) {
			$(this).html('<p class="close">닫기</p>').addClass('close-btn');
			$(this).parent().removeClass('slide-up').addClass('slide-down');
		} else {
			$(this).html('<p class="open">더보기</p>').removeClass('close-btn');
			$(this).parent().removeClass('slide-down').addClass('slide-up');
		}
	});
});
</script>

</head>
<body>

<div class="wrap">
  <h1>더보기 슬라이드 표시</h1>
  <div class="content-wrap animated">
    <div class="content-txt">
      <p>날카로우나 놀이 가는 듣기만 행복스럽고 있다. 곳으로 커다란 생의 교향악이다. 실현에 속잎나고, 그들에게 찬미를 힘있다. 되는 피부가 속에서 앞이 별과 불어 있다. 품었기 그들의 그러므로 시들어 꽃이 평화스러운 충분히 사막이다. 것이 거선의 청춘이 유소년에게서 놀이 고동을 얼마나 것이다.보라, 위하여서. 인생을 어디 피고 실현에 것이다. 군영과 있는 과실이 주는 것은 것이다. 가치를 피가 인생을 시들어 꽃이 피어나기 풀이 자신과 사막이다. 붙잡아 가슴이 그들의 그것을 봄바람을 황금시대의 피어나기 만천하의 못할 쓸쓸하랴?</p>
      <p>가치를 실로 길을 이상은 트고, 타오르고 희망의 보라. 고동을 속에서 끓는 노년에게서 이상 칼이다. 우는 그들은 소리다.이것은 힘있다. 그들의 못할 능히 천자만홍이 인간이 것이다. 뜨고, 새가 있는 가장 어디 많이 우리의 꾸며 그러므로 것이다. 구할 새 때에, 눈이 같이, 반짝이는 없으면 말이다. 위하여, 커다란 실현에 철환하였는가? 청춘을 용감하고 만천하의 영락과 같이, 같으며, 약동하다. 이상을 더운지라 생명을 운다. 가슴이 영락과 용감하고 풀밭에 돋고, 피가 열락의 있다.</p>
      <p>눈에 그와 인류의 가장 우리 인간의 불어 청춘 지혜는 있는가? 밥을 얼마나 내는 남는 능히 불어 봄바람을 그들에게 때문이다. 이상 이상이 불어 수 풀이 사랑의 끓는다. 길지 있을 무엇이 못하다 거친 품에 아름다우냐? 보내는 원대하고, 싹이 어디 하였으며, 밥을 아니다. 것은 그것은 하여도 찾아 방지하는 되려니와, 찾아다녀도, 창공에 온갖 것이다. 것은 그들을 이것을 보라. 있을 주며, 인간이 행복스럽고 생명을 생생하며, 불러 맺어, 고행을 부패뿐이다. 같이, 있는 대고, 인도하겠다는 교향악이다. 자신과 없으면 영원히 동산에는 것이다.</p>
    </div>
    <div class="more-btn">
      <p class="open">더보기</p>
    </div>
  </div>
</div>

</body>
</html>