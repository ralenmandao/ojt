import io.evanwong.oss.hipchat.v2.HipChatClient
import io.evanwong.oss.hipchat.v2.commons.NoContent
import io.evanwong.oss.hipchat.v2.rooms.MessageColor
import io.evanwong.oss.hipchat.v2.rooms.SendRoomNotificationRequestBuilder

import java.util.concurrent.Future

class Sample {
	static void main(args){
		String defaultAccessToken = "abcd1234";
		HipChatClient client = new HipChatClient(defaultAccessToken);
		SendRoomNotificationRequestBuilder builder = client.prepareSendRoomNotificationRequestBuilder("myRoom", "hello world!");
		Future<NoContent> future = builder.setColor(MessageColor.YELLOW).setNotify(true).build().execute();
		//optional... if you want/need to inspect the result:
		NoContent noContent = future.get();
	}
}
