# AudioRecorder
![](http://upload-images.jianshu.io/upload_images/4037756-1669f986572787d7.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

上一篇文章介绍了使用 MediaRecorder 实现录音功能 [Android录音实现(MediaRecorder)](http://www.jianshu.com/p/de779d509e6c) ，下面我们继续看看使用 AudioRecord 实现录音功能。

### AudioRecord

首先看看Android帮助文档中对该类的简单概述: AndioRecord 类的主要功能是让各种 Java 应用能够管理音频资源，以便它们通过此类能够录制平台的声音输入硬件所收集的声音。此功能的实现就是通过 "pulling 同步"（reading读取）AudioRecord 对象的声音数据来完成的。在录音过程中，应用所需要做的就是通过后面三个类方法中的一个去及时地获取 AudioRecord 对象的录音数据。 AudioRecord 类提供的三个获取声音数据的方法分别是 read(byte[], int, int), read(short[], int, int), read(ByteBuffer, int)。无论选择使用那一个方法都必须事先设定方便用户的声音数据的存储格式。

开始录音的时候，一个 AudioRecord 需要初始化一个相关联的声音buffer，这个 buffer 主要是用来保存新的声音数据。这个 buffer 的大小，我们可以在对象构造期间去指定。它表明一个 AudioRecord 对象还没有被读取（同步）声音数据前能录多长的音(即一次可以录制的声音容量)。声音数据从音频硬件中被读出，数据大小不超过整个录音数据的大小（可以分多次读出），即每次读取初始化 buffer 容量的数据。

采集工作很简单，我们只需要构造一个AudioRecord对象，然后传入各种不同配置的参数即可。一般情况下录音实现的简单流程如下：

1. 音频源:我们可以使用麦克风作为采集音频的数据源。

2. 采样率:一秒钟对声音数据的采样次数，采样率越高，音质越好。

3. 音频通道：单声道，双声道等，

4. 音频格式:一般选用PCM格式，即原始的音频样本。

5. 缓冲区大小:音频数据写入缓冲区的总数，可以通过AudioRecord.getMinBufferSize获取最小的缓冲区。（将音频采集到缓冲区中然后再从缓冲区中读取）。


AudioRecorder 录音声音数据从音频硬件中被读出，编码格式为 PCM格式，但 PCM语音数据，如果保存成音频文件，是不能够被播放器播放的，所以必须先写代码实现数据编码以及压缩。下面实现 PCM 语音数据转为 WAV 文件。

总结：AudioRecorder 录音相比较 MediaRecorder 使用起来会麻烦一些，但优点也是显而易见的，AudioRecorder 录音时直接操纵硬件获取音频流数据，该过程是实时处理，可以用代码实现各种音频的封装，同时也可实现暂停功能，关于实现暂停录音功能今天在这里就不赘述了，推荐大家阅读 imhxl 博主的分享 http://blog.csdn.net/imhxl/article/details/52190451 。
