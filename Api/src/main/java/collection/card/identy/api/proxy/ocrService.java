package collection.card.identy.api.proxy;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.EnglishOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.EnglishOCRResponse;
import com.tencentcloudapi.ocr.v20181119.models.TextDetectionEn;
import org.springframework.stereotype.Component;

@Component
public class ocrService {
    public TextDetectionEn[] getKeyWords(String imageUrl) {
        try {
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential("", "");
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            OcrClient client = new OcrClient(cred, "ap-shanghai", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            EnglishOCRRequest req = new EnglishOCRRequest();
            req.setImageUrl(imageUrl);
            // 返回的resp是一个EnglishOCRResponse的实例，与请求对象对应
            EnglishOCRResponse resp = client.EnglishOCR(req);
            // 输出json格式的字符串回包
            return resp.getTextDetections();
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return null;
    }
}
