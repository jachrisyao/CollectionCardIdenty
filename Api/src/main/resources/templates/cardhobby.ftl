<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="Keywords" content="球星卡,收藏卡,篮球卡,NBA球星卡,足球卡,签名卡,Panini,Panini球星卡,Upper Deck,Leaf,Topps,Fleer,拆卡,拆包,拆箱">
    <title> 发布卡片-填写详情 </title>
    <meta name="description" content="卡淘 - 您的球星卡交易平台" />
    <link href="http://www.cardhobby.com/Content/bootstrap.css" rel="stylesheet">
    <link href="http://www.cardhobby.com/Content/bootstrap-theme.css" rel="stylesheet">
    <link href="http://www.cardhobby.com/Content/custom.css" rel="stylesheet">

    <link href="http://www.cardhobby.com/Content/datepicker.css" rel="stylesheet">
    <link href="http://www.cardhobby.com/Scripts/colorbox/colorbox.css" rel="stylesheet" />
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <script src="https://g.alicdn.com/aliww/ww/json/json.js" charset="utf-8"></script>
    <![endif]-->
    <script src="http://www.cardhobby.com/Scripts/jquery-1.10.2.min.js"></script>
    <script src="http://www.cardhobby.com/Scripts/NimWebSDK.js"></script>
    <script src="http://www.cardhobby.com/Scripts/jquery.validate.js"></script>
    <script src="http://www.cardhobby.com/Scripts/additional-methods.js"></script>
    <script src="http://www.cardhobby.com/Scripts/messages_zh.js"></script>
    <script src="http://www.cardhobby.com/Scripts/bootstrap.js"></script>
    <script src="http://www.cardhobby.com/Scripts/bootstrap-datepicker.js"></script>
    <script src="http://www.cardhobby.com/Scripts/bootstrap-datepicker.zh-CN.js"></script>
    <script src="http://www.cardhobby.com/Scripts/bootstrap-paginator.js"></script>
    <script src="http://www.cardhobby.com/Scripts/countdown.js"></script>
    <script src="http://www.cardhobby.com/Scripts/colorbox/jquery.colorbox-min.js"></script>
    <script src="http://www.cardhobby.com/Scripts/fileinput.min.js"></script>
    <script src="http://www.cardhobby.com/Scripts/zh.js"></script>
    <!-- WSDK-->

    <link rel="shortcut icon" href="https://www.cardhobby.com/favicon.ico">
</head>

<body>
<div class="container">
    <link href="http://www.cardhobby.com/Content/new_card.css" rel="stylesheet">
    <link href="http://www.cardhobby.com/Scripts/colorbox/colorbox.css" rel="stylesheet">
    <link href="http://www.cardhobby.com/Content/bootstrap-select.css" rel="stylesheet" />
    <script src="http://www.cardhobby.com/Scripts/layer/layer.js"></script>
    <script src="http://www.cardhobby.com/Scripts/bootstrap-select.js"></script>
    <div class="row rel-title">发布宝贝</div>
    <form action="http://www.cardhobby.com/member/newaddcard/" method="post" id="ItemAddForm" enctype="multipart/form-data">
        <!--宝贝详情-->
        <div class="row reltab-box">
            <div class="reltab-title">宝贝详情</div>
            <div class="reltab-formbox clearfix">
                <div class="col-xs-3 formbox-ltext"><em>*</em><span>分类</span></div>
                <div class="col-xs-9 formbox-rtext">
                    <div class="col-xs-6 berror">

                        <select class="form-control selectpicker" id="Item_Category" name="Item_Category"
                                onchange="IsShowWZP();" required data-live-search="true">
                            <option value="">请选择</option>

                            <option value="10000">体育卡</option>
                            <option value="10001">潮流装备</option>
                            <option value="10002">娱乐卡</option>
                            <option value="10003">桌游卡</option>
                            }
                        </select>
                    </div>
                    <div id="AttributeInfo1"></div>

                </div>
            </div>
            <div class="reltab-formbox clearfix">
                <div class="col-xs-3 formbox-ltext"><em>*</em><span>标题</span></div>
                <div class="col-xs-9 formbox-rtext">
                    <div class="col-xs-12 berror">
                        <input type="text" name="Title" id="textarea-item" class="form-control" placeholder="请输入"
                               required minlength="5" maxlength="80">
                        <div class="text-right col99" id="textareaInput">5-80个字符</div>
                    </div>
                </div>
            </div>

            <div class="reltab-formbox clearfix">
                <div class="col-xs-3 formbox-ltext"><em>*</em><span>展示图</span></div>
                <div class="col-xs-9 formbox-rtext">
                    <div class="col-xs-12">
                        <div class="pull-left" id="masonryShowImageUrl">
                        </div>
                        <div class="pull-left">
                            <img src="http://www.cardhobby.com/Content/img/upload1.jpg"
                                 onclick="UploadImg('uploadShowImageUrl')" />
                            <input type="file" id="uploadShowImageUrl" name="uploadShowImageUrl" />
                        </div>

                    </div>
                </div>
            </div>
            <div class="reltab-formbox clearfix " id="isgrade_div">
                <div class="col-xs-3 formbox-ltext"><em>*</em><span>是否评级卡</span></div>
                <div class="col-xs-9 formbox-rtext">
                    <div class="col-xs-4 cerror">
                        <select name="IsGrade" class="form-control" required>
                            <option value="">请选择</option>
                            <option value="1">是</option>
                            <option value="0">否</option>

                        </select>
                    </div>
                </div>
            </div>

            <div id="AttributeInfo"></div>

            <div class="reltab-formbox clearfix">
                <div class="col-xs-3 formbox-ltext"><em>*</em><span>描述</span></div>
                <div class="col-xs-9 formbox-rtext">
                    <div class="col-xs-12 berror">
                            <textarea class="form-control" name="Detail" rows="4" placeholder="请输入" required
                                      maxlength="1000"></textarea>
                        <div class="text-right col99">1000个字以内，不支持html标签</div>
                    </div>
                </div>
            </div>
        </div>
        <!--宝贝详情end-->
        <!--出售信息-->
        <div class="row reltab-box">
            <div class="reltab-title">出售信息</div>
            <div class="reltab-formbox clearfix">
                <div class="col-xs-3 formbox-ltext"><em>*</em><span>出售方式</span></div>
                <div class="col-xs-9 formbox-rtext">
                    <div class="col-xs-12">

                        <label class="rechecked">
                            <input type="radio" value="1" name="ByWay" checked vid="123"
                                   onchange="changeShow(this)"><i></i><span>一口价</span>
                        </label>
                        <label class="rechecked">
                            <input type="radio" value="2" name="ByWay" vid="222"
                                   onchange="changeShow(this)"><i></i><span>竞拍</span>

                        </label>
                    </div>
                </div>
            </div>





            <div class="reltab-formbox clearfix hide" isshowvid="123">
                <div class="col-xs-3 formbox-ltext"><em>*</em><span>是否议价</span></div>
                <div class="col-xs-9 formbox-rtext">
                    <div class="col-xs-12">
                        <label class="rechecked">
                            <input type="checkbox" id="Bargain" name="Bargain" value="1" vid="456"
                                   onchange="changeShow(this)"><i></i><span>允许买家向我提出报价</span>
                        </label>
                    </div>
                </div>
            </div>

            <div class="reltab-formbox clearfix">
                <div class="col-xs-3 formbox-ltext"><em>*</em><span>价格</span></div>
                <div class="col-xs-9 formbox-rtext form-inline">
                    <div class="col-xs-4 aerror hide" isshowvid="123">
                        <label>一口价</label>
                        <div>￥ <input type="text" name="Price" value="0" class="form-control form-inline"
                                      placeholder="请输入" required min="0.1" max="9999999"></div>
                    </div>

                    <div class="col-xs-4 aerror hide" isshowvid="222">
                        <label>起拍价</label>
                        <div>￥ 1元</div>
                    </div>

                    <div class="col-xs-4 hide aerror" isshowvid="456">
                        <label>自动拒绝价</label> <a data-container="body" class="proper" data-toggle="popover"
                                                tabindex="0" role="button" data-trigger="focus" data-placement="right"
                                                data-content="低于此价格的报价将被自动拒绝，不设定请留空"></a>
                        <div>￥ <input type="text" name="LPrice" class="form-control form-inline" placeholder="请输入"
                                      min="0.1" max="9999999"></div>
                    </div>
                    <div class="col-xs-4 hide aerror" isshowvid="456">
                        <label>自动接受价</label> <a data-container="body" class="proper" data-toggle="popover"
                                                tabindex="0" role="button" data-trigger="focus" data-placement="right"
                                                data-content="高于此价格的报价将被自动接受，不设定请留空"></a>
                        <div>￥ <input type="text" name="HPrice" class="form-control form-inline" placeholder="请输入"
                                      min="0.1" max="9999999"></div>
                    </div>
                </div>
            </div>
            <div class="reltab-formbox clearfix hide" isshowvid="222">
                <div class="col-xs-3 formbox-ltext">
                    <em>*</em><span>是否开启竞拍担保金</span>
                </div>
                <div class="col-xs-9 formbox-rtext">
                    <div class="col-xs-12">
                        <label class="rechecked">
                            <input type="checkbox" name="IsDeposit" id="IsDeposit" value="1" vid="222456"
                                   onchange="changeShow(this)" />
                            <i></i><span>开启</span>
                        </label>
                    </div>
                </div>
            </div>
            <div class="reltab-formbox clearfix hide" isshowvid="222456">
                <div class="col-xs-3 formbox-ltext">
                    <em>*</em><span>保证金设置</span>
                </div>
                <div class="col-xs-9 formbox-rtext  form-inline">
                    <div class="col-xs-4 cerror">
                        <label>
                            竞拍担保金（发布点）<a data-container="body" class="proper tipbox" data-toggle="popover"
                                         tabindex="-1" role="button" data-trigger="hover" data-placement="right"
                                         data-original-title="竞拍担保金"
                                         data-content="卖家对拍品设置拍品担保金后，买家在竞拍前必须缴纳担保金（发布点）后，方可参与竞拍。">！</a>
                        </label>
                        <div><input type="number" name="DepositNum" value="5" class="form-control saveinfo"
                                    placeholder="请输入" required min="1" max="999" /></div>
                    </div>
                    <div class="col-xs-4 cerror">
                        <label>
                            免担保金信用分起点 <a data-container="body" class="proper tipbox" data-toggle="popover"
                                         tabindex="-1" role="button" data-trigger="hover" data-placement="right"
                                         data-original-title="免担保门槛"
                                         data-content="卖家对拍品设置买家好评数以后，买家好评数超过免担保门槛，可以免付担保金参与竞拍">！</a>
                        </label>
                        <div><input type="number" name="CreditNum" value="0" class="form-control saveinfo"
                                    placeholder="请输入" required min="1" /></div>
                    </div>
                </div>
            </div>


            <div class="reltab-formbox clearfix">
                <div class="col-xs-3 formbox-ltext"><em>*</em><span>数量</span></div>
                <div class="col-xs-9 formbox-rtext">
                    <div class="col-xs-4 cerror">
                        <input type="number" name="QuantitySold" id="QuantitySold" class="form-control saveinfo"
                               placeholder="请输入" required min="1">
                    </div>
                </div>
            </div>

            <div class="reltab-formbox clearfix hidden">
                <div class="col-xs-3 formbox-ltext"><em>*</em><span>支付方式</span></div>
                <div class="col-xs-9 formbox-rtext">
                    <div class="col-xs-12">

                        <label class="rechecked">
                            <input type="radio" value="1" name="IsGuarantee" vid="xszf" checked
                                   onchange="changeShow(this)"><i></i><span>线上支付</span>
                        </label>
                    </div>
                </div>
            </div>

            <div class="reltab-formbox clearfix">
                <div class="col-xs-3 formbox-ltext"><em>*</em><span>有效期</span></div>
                <div class="col-xs-9 formbox-rtext">
                    <!--一口价-->
                    <div class="col-xs-4 hide cerror" isshowvid="123">
                        <select name="EffectiveDay" class="form-control" required>
                            <option value="">请选择</option>
                            <option value="7">7天</option>
                            <option value="15">15天</option>
                            <option value="30">30天</option>
                        </select>
                    </div>
                    <!--竞拍-->
                    <div class="col-xs-4 hide cerror" isshowvid="222">
                        <select name="EffectiveDay" class="form-control" required>
                            <option value="">请选择</option>
                            <option value="1">1天</option>
                            <option value="3">3天</option>
                            <option value="5">5天</option>
                            <option value="7">7天</option>
                        </select>
                    </div>
                    <div class="margin10 col-xs-12">

                        <div class="col-xs-12 row">
                            <div class="col-xs-3 row">
                                <label class="rechecked" style="margin-right:25px;">
                                    <input type="checkbox" value="1" name="ReleaseType" vid="789"
                                           onchange="changeShow(this)"><i></i><span>设定上架时间</span>
                                </label>
                            </div>
                            <div class="col-xs-9 col-content-padding row hide" isshowvid="789">
                                <div class="col-xs-4 cerror">
                                    <input type="text" name="LastOnTime" class="form-control datepicker"
                                           placeholder="选择日期" value="" required>
                                </div>
                                <div class="col-xs-2 cerror">
                                    <select class="form-control hour" name="hour" required>

                                        <option value="00">00</option>
                                        <option value="01">01</option>
                                        <option value="02">02</option>
                                        <option value="03">03</option>
                                        <option value="04">04</option>
                                        <option value="05">05</option>
                                        <option value="06">06</option>
                                        <option value="07">07</option>
                                        <option value="08">08</option>
                                        <option value="09">09</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                        <option value="13">13</option>
                                        <option value="14">14</option>
                                        <option value="15">15</option>
                                        <option value="16">16</option>
                                        <option value="17">17</option>
                                        <option value="18">18</option>
                                        <option value="19">19</option>
                                        <option value="20">20</option>
                                        <option value="21">21</option>
                                        <option value="22">22</option>
                                        <option value="23">23</option>
                                    </select>
                                </div>
                                <div class="col-xs-1" style="width:15px;text-align:center;line-height:30px;">:</div>
                                <div class="col-xs-2 cerror">
                                    <select class="form-control minute" name="minute" required>

                                        <option value="00">00</option>
                                        <option value="01">01</option>
                                        <option value="02">02</option>
                                        <option value="03">03</option>
                                        <option value="04">04</option>
                                        <option value="05">05</option>
                                        <option value="06">06</option>
                                        <option value="07">07</option>
                                        <option value="08">08</option>
                                        <option value="09">09</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                        <option value="13">13</option>
                                        <option value="14">14</option>
                                        <option value="15">15</option>
                                        <option value="16">16</option>
                                        <option value="17">17</option>
                                        <option value="18">18</option>
                                        <option value="19">19</option>
                                        <option value="20">20</option>
                                        <option value="21">21</option>
                                        <option value="22">22</option>
                                        <option value="23">23</option>
                                        <option value="24">24</option>
                                        <option value="25">25</option>
                                        <option value="26">26</option>
                                        <option value="27">27</option>
                                        <option value="28">28</option>
                                        <option value="29">29</option>
                                        <option value="30">30</option>
                                        <option value="31">31</option>
                                        <option value="32">32</option>
                                        <option value="33">33</option>
                                        <option value="34">34</option>
                                        <option value="35">35</option>
                                        <option value="36">36</option>
                                        <option value="37">37</option>
                                        <option value="38">38</option>
                                        <option value="39">39</option>
                                        <option value="40">40</option>
                                        <option value="41">41</option>
                                        <option value="42">42</option>
                                        <option value="43">43</option>
                                        <option value="44">44</option>
                                        <option value="45">45</option>
                                        <option value="46">46</option>
                                        <option value="47">47</option>
                                        <option value="48">48</option>
                                        <option value="49">49</option>
                                        <option value="50">50</option>
                                        <option value="51">51</option>
                                        <option value="52">52</option>
                                        <option value="53">53</option>
                                        <option value="54">54</option>
                                        <option value="55">55</option>
                                        <option value="56">56</option>
                                        <option value="57">57</option>
                                        <option value="58">58</option>
                                        <option value="59">59</option>

                                    </select>
                                </div>
                                <div class="col-xs-1" style="width:15px;text-align:center;line-height:30px;">:</div>
                                <div class="col-xs-2 cerror">
                                    <select class="form-control Second" name="Second" required>

                                        <option value="00">00</option>
                                        <option value="01">01</option>
                                        <option value="02">02</option>
                                        <option value="03">03</option>
                                        <option value="04">04</option>
                                        <option value="05">05</option>
                                        <option value="06">06</option>
                                        <option value="07">07</option>
                                        <option value="08">08</option>
                                        <option value="09">09</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                        <option value="13">13</option>
                                        <option value="14">14</option>
                                        <option value="15">15</option>
                                        <option value="16">16</option>
                                        <option value="17">17</option>
                                        <option value="18">18</option>
                                        <option value="19">19</option>
                                        <option value="20">20</option>
                                        <option value="21">21</option>
                                        <option value="22">22</option>
                                        <option value="23">23</option>
                                        <option value="24">24</option>
                                        <option value="25">25</option>
                                        <option value="26">26</option>
                                        <option value="27">27</option>
                                        <option value="28">28</option>
                                        <option value="29">29</option>
                                        <option value="30">30</option>
                                        <option value="31">31</option>
                                        <option value="32">32</option>
                                        <option value="33">33</option>
                                        <option value="34">34</option>
                                        <option value="35">35</option>
                                        <option value="36">36</option>
                                        <option value="37">37</option>
                                        <option value="38">38</option>
                                        <option value="39">39</option>
                                        <option value="40">40</option>
                                        <option value="41">41</option>
                                        <option value="42">42</option>
                                        <option value="43">43</option>
                                        <option value="44">44</option>
                                        <option value="45">45</option>
                                        <option value="46">46</option>
                                        <option value="47">47</option>
                                        <option value="48">48</option>
                                        <option value="49">49</option>
                                        <option value="50">50</option>
                                        <option value="51">51</option>
                                        <option value="52">52</option>
                                        <option value="53">53</option>
                                        <option value="54">54</option>
                                        <option value="55">55</option>
                                        <option value="56">56</option>
                                        <option value="57">57</option>
                                        <option value="58">58</option>
                                        <option value="59">59</option>

                                    </select>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>

        <!--出售信息end-->
        <!--运费-->


        <div class="row reltab-box" id="editFreight">
            <div class="reltab-title">
                运费

            </div>
            <!--国内-->
            <div class="freight-title">国内运费</div>
            <div class="freight-list listborder">
                <div class="reltab-formbox clearfix" id="cloneProvince">
                    <div class="col-xs-3 formbox-ltext">
                        <em>*</em><span class="regtitle">全国默认地区</span>
                        <div class="col99 region" pval=""></div>
                    </div>
                    <div class="col-xs-9 formbox-rtext">
                        <div class="col-xs-6 formbox-rtextmrt">

                            <!--initval用于初始化赋值-->
                            <ul class="checklist clearfix" initval="3">
                                <li class="pull-left" nvalue="1" onclick="onchecked(this)"><i></i>包邮</li>
                                <li class="pull-left" nvalue="2" onclick="onchecked(this)"><i></i>顺丰到付</li>
                                <li class="pull-left" nvalue="3" onclick="onchecked(this)"><i></i></li>
                            </ul>
                            <input type="hidden" value="3" name="Designatedarea1" class="Designatedarea" />
                        </div>
                        <div class="col-xs-6 cerror">
                            <div class="form-inline">￥ <input type="text" name="Price1"
                                                              class="form-control form-inline Price" placeholder="请输入" value="15"></div>
                        </div>
                    </div>
                </div>

                <div class="reltab-formbox clearfix" nameindex="1">
                    <div class="col-xs-3 formbox-ltext">
                        <em>*</em><span class="regtitle">指定地区</span><a href="javascript:void(0)"
                                                                       onclick="editProvinceCheck(this,1)">编辑</a><a href="javascript:void(0)"
                                                                                                                    onclick="deleteProvinceCheck(this)">删除</a>
                        <div class="col99 region" pval="1">北京市</div>
                        <input type="hidden" name="provinceText2" class="provinceText" value="北京市" />
                        <input type="hidden" name="provinceId2" class="provinceId" value="1" />
                    </div>
                    <div class="col-xs-9 formbox-rtext">
                        <div class="col-xs-6 formbox-rtextmrt">

                            <ul class="checklist clearfix" initval="3">
                                <li class="pull-left" nvalue="1" onclick="onchecked(this)"><i></i>包邮</li>
                                <li class="pull-left" nvalue="2" onclick="onchecked(this)"><i></i>顺丰到付</li>
                                <li class="pull-left" nvalue="3" onclick="onchecked(this)"><i></i></li>
                            </ul>
                            <input type="hidden" value="3" name="Designatedarea2" />
                        </div>
                        <div class="col-xs-6 cerror">
                            <div class="form-inline">￥ <input type="text" name="Price2"
                                                              class="form-control form-inline Price" placeholder="请输入" value="13"></div>
                        </div>
                    </div>
                </div>
                <div class="reltab-formbox clearfix" nameindex="1">
                    <div class="col-xs-3 formbox-ltext">
                        <em>*</em><span class="regtitle">指定地区</span><a href="javascript:void(0)"
                                                                       onclick="editProvinceCheck(this,1)">编辑</a><a href="javascript:void(0)"
                                                                                                                    onclick="deleteProvinceCheck(this)">删除</a>
                        <div class="col99 region" pval="29、34、20">江苏省、上海市、浙江省</div>
                        <input type="hidden" name="provinceText2" class="provinceText" value="江苏省、上海市、浙江省" />
                        <input type="hidden" name="provinceId2" class="provinceId" value="29、34、20" />
                    </div>
                    <div class="col-xs-9 formbox-rtext">
                        <div class="col-xs-6 formbox-rtextmrt">

                            <ul class="checklist clearfix" initval="3">
                                <li class="pull-left" nvalue="1" onclick="onchecked(this)"><i></i>包邮</li>
                                <li class="pull-left" nvalue="2" onclick="onchecked(this)"><i></i>顺丰到付</li>
                                <li class="pull-left" nvalue="3" onclick="onchecked(this)"><i></i></li>
                            </ul>
                            <input type="hidden" value="3" name="Designatedarea2" />
                        </div>
                        <div class="col-xs-6 cerror">
                            <div class="form-inline">￥ <input type="text" name="Price2"
                                                              class="form-control form-inline Price" placeholder="请输入" value="10"></div>
                        </div>
                    </div>
                </div>
                <div class="reltab-formbox clearfix" nameindex="1">
                    <div class="col-xs-3 formbox-ltext">
                        <em>*</em><span class="regtitle">指定地区</span><a href="javascript:void(0)"
                                                                       onclick="editProvinceCheck(this,1)">编辑</a><a href="javascript:void(0)"
                                                                                                                    onclick="deleteProvinceCheck(this)">删除</a>
                        <div class="col99 region" pval="12、22、23">内蒙古、西藏自治区、新疆维吾尔自治区</div>
                        <input type="hidden" name="provinceText2" class="provinceText" value="内蒙古、西藏自治区、新疆维吾尔自治区" />
                        <input type="hidden" name="provinceId2" class="provinceId" value="12、22、23" />
                    </div>
                    <div class="col-xs-9 formbox-rtext">
                        <div class="col-xs-6 formbox-rtextmrt">

                            <ul class="checklist clearfix" initval="3">
                                <li class="pull-left" nvalue="1" onclick="onchecked(this)"><i></i>包邮</li>
                                <li class="pull-left" nvalue="2" onclick="onchecked(this)"><i></i>顺丰到付</li>
                                <li class="pull-left" nvalue="3" onclick="onchecked(this)"><i></i></li>
                            </ul>
                            <input type="hidden" value="3" name="Designatedarea2" />
                        </div>
                        <div class="col-xs-6 cerror">
                            <div class="form-inline">￥ <input type="text" name="Price2"
                                                              class="form-control form-inline Price" placeholder="请输入" value="18"></div>
                        </div>
                    </div>
                </div>
                <div class="reltab-formbox clearfix" nameindex="1">
                    <div class="col-xs-3 formbox-ltext">
                        <em>*</em><span class="regtitle">指定地区</span><a href="javascript:void(0)"
                                                                       onclick="editProvinceCheck(this,1)">编辑</a><a href="javascript:void(0)"
                                                                                                                    onclick="deleteProvinceCheck(this)">删除</a>
                        <div class="col99 region" pval="35、9、24">澳门特别行政区、香港特别行政区、台湾省</div>
                        <input type="hidden" name="provinceText2" class="provinceText"
                               value="澳门特别行政区、香港特别行政区、台湾省" />
                        <input type="hidden" name="provinceId2" class="provinceId" value="35、9、24" />
                    </div>
                    <div class="col-xs-9 formbox-rtext">
                        <div class="col-xs-6 formbox-rtextmrt">

                            <ul class="checklist clearfix" initval="2">
                                <li class="pull-left" nvalue="1" onclick="onchecked(this)"><i></i>包邮</li>
                                <li class="pull-left" nvalue="2" onclick="onchecked(this)"><i></i>顺丰到付</li>
                                <li class="pull-left" nvalue="3" onclick="onchecked(this)"><i></i></li>
                            </ul>
                            <input type="hidden" value="2" name="Designatedarea2" />
                        </div>
                        <div class="col-xs-6 cerror">
                            <div class="form-inline">￥ <input type="text" name="Price2"
                                                              class="form-control form-inline Price" placeholder="请输入" value="0"></div>
                        </div>
                    </div>
                </div>

                <div class="reltab-formbox clearfix">
                    <div class="col-xs-12">
                        <a href="javascript:void(0)" onclick="addProvinceCheck(this)">+ 为指定地区设置运费</a>
                    </div>

                </div>
            </div>
            <!--国际运费-->
            <div class="freight-title">
                <label class="rechecked">
                    <input type="checkbox" id="freight" name="freight" value="1" checked vid="freight"
                           onchange="changeShow(this)"><i></i><span>支持国际运费</span>
                </label>
            </div>


            <div class="freight-list hide" isshowvid="freight">
                <div class="reltab-formbox clearfix" id="cloneProvince">
                    <div class="col-xs-3 formbox-ltext">
                        <em>*</em><span class="regtitle">默认地区/国家</span>
                        <div class="col99 region" pval=""></div>
                    </div>
                    <div class="col-xs-9 formbox-rtext">
                        <div class="col-xs-6 formbox-rtextmrt">

                            <ul class="checklist clearfix" initval="1">
                                <li class="pull-left" nvalue="1" onclick="onchecked(this)"><i></i>包邮</li>
                                <li class="pull-left" nvalue="2" onclick="onchecked(this)"><i></i>顺丰到付</li>
                                <li class="pull-left" nvalue="3" onclick="onchecked(this)"><i></i></li>
                            </ul>
                            <input type="hidden" value="1" name="Designatedarea3" class="Designatedarea" />
                        </div>
                        <div class="col-xs-6 cerror">
                            <div class="form-inline">￥ <input type="text" name="Price3"
                                                              class="form-control form-inline Price" placeholder="请输入" value="0"></div>
                        </div>
                    </div>
                </div>




                <div class="reltab-formbox clearfix">
                    <div class="col-xs-12">
                        <a href="javascript:void(0)" onclick="addProvinceCheck(this,2)">+ 为指定地区/国家设置运费</a>
                    </div>

                </div>
            </div>
        </div>
        <div class="col-xs-12 row kt-reading" isshowvid="xszf">
            <label class="rechecked">
                <input type="checkbox" id="isAgree" name="isAgree" value="1" checked><i></i><span>我已阅读并知晓<a
                            href="/market/rules#buyer_rule"
                            target="_blank">《交易规则说明》</a>，同意该笔交易使用卡淘官方银联账号作为收款方式进行交易。</span>
            </label>
        </div>
        <!--运费end-->
        <input type="hidden" name="id" id="id" value="0" />
        <input type="hidden" id="sendtype" name="sendtype" value="1" />
        <input type="hidden" name="AddressVersionCode" />
        <input type="hidden" id="NewPostageMoney" name="NewPostageMoney" />
        <div class="row relbtn-box" style="margin-top:15px;">

            <div class="col-sm-6">
                <button type="submit" class="btn-08" onclick="$('input[name=sendtype]').val(0);"
                        id="warehouse">放入仓库</button>

                <button type="submit" class="btn-09 buttondisplay"
                        onclick="$('input[name=sendtype]').val(1)">立即发布</button>
                <button type="submit" class="btn-09 buttondisplay"
                        onclick="$('input[name=sendtype]').val(2)">发布并继续录入</button>
            </div>
            <div class="col-sm-6 col99">卡淘将根据商品成交价扣除发布点，扣除成交金额5%的发布点</div>
            <div class="clearfix"></div>
        </div>
    </form>



    <!--省份弹框-->
    <div class="province-main" id="province-main" style="display:none;">
        <div class="clearfix province-b10">

            <div class="pull-left province-ler">A-G</div>
            <div class="pull-left province-rer">
                <a href="javascript:void(0)" class="prover" pval="1">北京市</a>
                <a href="javascript:void(0)" class="prover" pval="2">安徽省</a>
                <a href="javascript:void(0)" class="prover" pval="3">福建省</a>
                <a href="javascript:void(0)" class="prover" pval="35">澳门特别行政区</a>
                <a href="javascript:void(0)" class="prover" pval="4">广东省</a>
                <a href="javascript:void(0)" class="prover" pval="5">广西壮族自治区</a>
                <a href="javascript:void(0)" class="prover" pval="6">贵州省</a>
                <a href="javascript:void(0)" class="prover" pval="7">甘肃省</a>
                <a href="javascript:void(0)" class="prover" pval="8">钓鱼岛</a>

            </div>
            <div class="pull-left province-ler">H-K</div>
            <div class="pull-left province-rer">
                <a href="javascript:void(0)" class="prover" pval="30">河北省</a>
                <a href="javascript:void(0)" class="prover" pval="31">湖南省</a>
                <a href="javascript:void(0)" class="prover" pval="32">江西省</a>
                <a href="javascript:void(0)" class="prover" pval="33">海南省</a>
                <a href="javascript:void(0)" class="prover" pval="25">湖北省</a>
                <a href="javascript:void(0)" class="prover" pval="26">河南省</a>
                <a href="javascript:void(0)" class="prover" pval="27">吉林省</a>
                <a href="javascript:void(0)" class="prover" pval="28">黑龙江</a>
                <a href="javascript:void(0)" class="prover" pval="29">江苏省</a>

            </div>
            <div class="pull-left province-ler">L-S</div>
            <div class="pull-left province-rer">
                <a href="javascript:void(0)" class="prover" pval="10">山西省</a>
                <a href="javascript:void(0)" class="prover" pval="11">辽宁省</a>
                <a href="javascript:void(0)" class="prover" pval="12">内蒙古</a>
                <a href="javascript:void(0)" class="prover" pval="13">山东省</a>
                <a href="javascript:void(0)" class="prover" pval="14">四川省</a>
                <a href="javascript:void(0)" class="prover" pval="15">陕西省</a>
                <a href="javascript:void(0)" class="prover" pval="16">青海省</a>
                <a href="javascript:void(0)" class="prover" pval="17">宁夏省</a>
                <a href="javascript:void(0)" class="prover" pval="34">上海市</a>

            </div>
            <div class="pull-left province-ler">T-Z</div>
            <div class="pull-left province-rer">
                <a href="javascript:void(0)" class="prover" pval="9">香港特别行政区</a>
                <a href="javascript:void(0)" class="prover" pval="18">天津市</a>
                <a href="javascript:void(0)" class="prover" pval="19">重庆市</a>
                <a href="javascript:void(0)" class="prover" pval="20">浙江省</a>
                <a href="javascript:void(0)" class="prover" pval="21">云南省</a>
                <a href="javascript:void(0)" class="prover" pval="22">西藏自治区</a>
                <a href="javascript:void(0)" class="prover" pval="23">新疆维吾尔自治区</a>
                <a href="javascript:void(0)" class="prover" pval="24">台湾省</a>

            </div>
        </div>

    </div>
    <!--国家弹框-->
    <div id="country_code_layer">
        <div>
            <a class="arrow"></a>
            <div class="search-bar">
            </div>
        </div>
        <div id="result" style="display: block;">
            <div class="capital-list">
                <ul id="szmList"></ul>
            </div>
            <div class="current-capital">
                <span id="currentCapital">A</span><span class="line"></span>
            </div>
            <div class="country-list">
                <div class="viewport">
                    <ul id="gjList"></ul>
                </div>
            </div>
        </div>
        <div id="no-result" style="display: none;">未找到国家或地区</div>
    </div>

    <script src="http://www.cardhobby.com/Scripts/nationCode.js"></script>
    <link href="http://www.cardhobby.com/Scripts/uploadify-v3.1/uploadify.css" rel="stylesheet" />
    <script src="../static/js/public.js"></script>
    <script src="http://www.cardhobby.com/Scripts/uploadify-v3.1/jquery.uploadify-3.1.min.js"></script>
    <script>

        function agree(isChecked) {
            if (isChecked) {
                $("#relbtn-box").removeAttr("disabled");
            } else {
                $("#relbtn-box").attr("disabled", "disabled");
            }
        }

        $(function () {
            $("#popover").popover();
            $('[data-toggle="popover"]').popover();

            IsShowWZP();

            changeShow();


            $(".saveinfo").each(function (i, item) {
                $(item).keyup(function () {
                    var num = item.value;
                    var re = /^\d*$/;
                    if (!re.test(num)) {
                        isNaN(parseInt(num)) ? item.value = 0 : item.value = parseInt(num);
                    }
                });
            })
            //Select_Category();
            var bef = regEditFreight("#editFreight");
            $("#ItemAddForm").validate({
                submitHandler: function (form) {
                    var tag = true;
                    var aft = regEditFreight("#editFreight");
                    var regnumber = /^[0-9]*$/;
                    $('[name="Price1"]:visible,[name="Price2"]:visible,[name="Price3"]:visible,[name="Price4"]:visible').each(function () {
                        if (!regnumber.test($(this).val())) {
                            tag = false;
                            $(this).addClass("error");
                            if ($(this).next().is("label")) {
                                $(this).next().text("请输入正确的运费").css("display", "block");
                            } else {
                                $(this).after('<label for="Price2" class="error" style="display:block">请输入正确的运费</label>');
                            }

                        }
                    });
                    $(".freight-list:visible [name='provinceId2'],.freight-list:visible [name='provinceId4']").each(function () {
                        if (!$(this).val()) {
                            tag = false;
                            $(this).parent().find(".region").text("请选择指定区域").addClass("regionerror");
                        }
                    });
                    if (bef != aft) {
                        $('[name="AddressVersionCode"]').val("");
                    }
                    var column = $("#Column").val();
                    if (column == undefined || column.length <= 0) {
                        Tips("请选择卡片的正确分类！");
                        tag = false;
                    }
                    if (tag) {
                        Tips("正在提交您的请求，请稍后……");
                        Save();
                    }
                    return false;
                }
            });
            textLength();
            LimitNumberValidate();
        });

        function textLength() {//id 对应的dom，number允许输入的个数

            var lenInput = $('#textarea-item').val().length;

            $('#textareaInput').html(lenInput + "/80个字符");

            $("#textarea-item").keyup(function () {
                lenInput = $(this).val().length;
                //if (lenInput > 0 && lenInput <= 80) {
                //    $('#textareaInput').html(lenInput + "/80个字符");
                //} else {
                //    $('#textareaInput').html(lenInput + "/80个字符");
                //}
                $('#textareaInput').html(lenInput + "/80个字符");
            });
        }
        function LimitNumberValidate() {
            $("#QuantitySold").on("input", function () {
                $("#LimitNumber").prop("max", $("#QuantitySold").val())
            })

        }


        function loadAttributeInfo(column, id, Category, LoadView) {

            $.get("http://www.cardhobby.com/member/AttributeInfo/?type=" + column + "&cardid=" + id + "&Category=" + Category + "&LoadView=" + LoadView, function (data) {

                $("#" + LoadView + "").html(data);
            });

        }

        function Save() {

            var form = $("#ItemAddForm").serialize();
            $("#ItemAddForm").find("button[type=submit]").attr("disabled", true);
            //$("#sendtype").val(type);
            $.post("http://www.cardhobby.com/member/newaddcard/", form, function (data) {
                var result = data.split("|");
                if (result[0] == "1") {
                    Tips("提交成功！");
                    setTimeout(function () {
                        location.href = result[1];

                    }, 1000);
                } else if (result[0] == "-1") {
                    Tips(result[2]);
                    setTimeout(function () {
                        location.href = result[1];

                    }, 1000);
                } else {
                    Tips(result[0]);
                    $("#ItemAddForm").find("button[type=submit]").removeAttr("disabled");
                }
            });
        }

        function IsShowWZP() {

            var column = $("#Item_Category").val();
            var cardid = $("#id").val();

            if (column != "") {

                $("#AttributeInfo1").show();
                if (column == "10000" || column == "10002" || column == "10003") {
                    $("#isgrade_div").show();
                } else {
                    $("#isgrade_div").hide();
                }
                loadAttributeInfo(column, cardid, 0, "AttributeInfo1");


            } else {

                $("#Column").val('');
                $("#isgrade_div").hide();
                $("#AttributeInfo1,#AttributeInfo").hide();

            }

        }

        function IsShowWZP1() {

            var column = $("#Column").val();

            var cardid = $("#id").val();

            if (column != "") {

                $("#AttributeInfo").show();

                loadAttributeInfo(column, cardid, 0, "AttributeInfo");

            } else {
                $("#AttributeInfo").html("");
                $("#AttributeInfo").hide();
            }

        }





        function changeShow(ts) {
            //数量特殊处理
            if ($("[name='ByWay']:checked").val() == "2" || $("#Bargain").is(":checked")) {
                $("#QuantitySold").val("1").prop("readonly", true);
                $(".bargain_box").hide();

            } else {
                $("#QuantitySold").prop("readonly", false);
                $(".bargain_box").show();
                $("input[name='IsLimit']").removeAttr("disabled");
                $("input[name='IsLimit']").each(function (i, item) {
                    $(item).val($(item).data("value"));
                });
            }
            //数量特殊处理end
            if (ts) {
                var name = $(ts).prop("name");
                var domlist = $("[name=" + name + "]");

                domlist.each(function () {
                    var vid = $(this).attr("vid");
                    if ($(this).is(":checked")) {
                        $("[isshowvid=" + vid + "]").removeClass("hide");
                        $("[isshowvid=" + vid + "]").show();
                        $("[isshowvid=" + vid + "]").find("input,select").prop("disabled", false);
                        $("[isshowvid=" + vid + "]").find(".checklist").find("li").removeClass("cur").end().find('li:first').addClass("cur");
                        $("[isshowvid=" + vid + "]").find(".Price").val("").prop("readonly", true);
                        $("[isshowvid=" + vid + "]").find(".IsLimit").prop("checked", true);
                        $("[isshowvid=" + vid + "]").find("[name='provinceId4']").parent().find(".region").attr("pval", "").text("未选择任何区域");
                        $("[name='Designatedarea3'],[name='Designatedarea4'],[name='isAgree'],[name='IsDeposit']").val("1");
                    } else {
                        $("[isshowvid=" + vid + "]").addClass("hide");
                        $("[isshowvid=" + vid + "]").hide();
                        $("[isshowvid=" + vid + "]").find("input,select").val("").prop("disabled", true);
                        $("[isshowvid=" + vid + "]").find("[vid]:checked").each(function () {
                            $(this).prop("checked", false);
                            var cvid = $(this).attr("vid");
                            $("[isshowvid=" + cvid + "]").find("input,select").val("");
                            $("[isshowvid=" + cvid + "]").addClass("hide");
                        });
                        //允许买家向我提出报价特殊处理
                        $("[name='Bargain']").val("1");
                    }
                    //定时上架隐藏按钮
                    if ($('[name="ReleaseType"]:checked').length > 0) {
                        $(".buttondisplay").addClass("is-hide");
                    } else {
                        $(".buttondisplay").removeClass("is-hide");
                    }
                })

            } else {
                //if ($('[name="ReleaseType"]:checked').length > 0) {
                //    $("#warehouse").addClass("hide");
                //}

                var dom = $("[vid]:checked");
                dom.each(function () {
                    var chvalshow = $(this).attr("vid");
                    $("[isshowvid=" + chvalshow + "]").removeClass("hide");
                    //定时上架隐藏按钮
                    if ($(this).prop("name") == "ReleaseType") {
                        $(".buttondisplay").addClass("is-hide");
                    } else {
                        $(".buttondisplay").removeClass("is-hide");
                    }

                });
                $("[isshowvid]:hidden").find("input:not(.saveinfo),select").val("").prop("disabled", true);
                //$("[name='Designatedarea3'],[name='Designatedarea4']").val("1");
            }
        }
        $(".datepicker").datepicker({
            language: 'zh-CN',
            autoclose: true,
            todayHighlight: true,
            format: "yyyy-mm-dd",
            forceParse: false,
        });
        function addProvinceCheck(ts, type) {
            var _this = $(ts);

            var newprovince = $("#cloneProvince").clone();
            newprovince.prop("id", "");
            newprovince.attr("provincesid", "");
            if (type == 2) {
                newprovince.find(".regtitle").text("指定地区/国家");
                newprovince.find(".region").before('<a href="javascript:void(0)" onclick="editProvinceCheck(this,2)">编辑</a><a href="javascript:void(0)" onclick="deleteProvinceCheck(this)">删除</a>');
                newprovince.find(".region").after('<input type="hidden" name="provinceText4" class="provinceText" value=""/><input type="hidden" name="provinceId4" class="provinceId" value=""/>');
                newprovince.find(".Designatedarea").prop("name", "Designatedarea4");
                newprovince.find(".Price").prop("name", "Price4");

            } else {
                newprovince.find(".regtitle").text("指定地区");
                newprovince.find(".region").before('<a href="javascript:void(0)" onclick="editProvinceCheck(this)">编辑</a><a href="javascript:void(0)" onclick="deleteProvinceCheck(this)">删除</a>');
                newprovince.find(".region").after('<input type="hidden" name="provinceText2" class="provinceText" value=""/><input type="hidden" name="provinceId2" class="provinceId" value=""/>');
                newprovince.find(".Designatedarea").prop("name", "Designatedarea2");
                newprovince.find(".Price").prop("name", "Price2");
            }
            newprovince.find(".region").text("未选择任何区域");
            newprovince.find("[type='radio']").prop("name", "Designatedarea" + newprovince.attr("nameindex"));


            _this.parents(".reltab-formbox").before(newprovince);
        }
        //修改 type==1 省份， 2国家
        function editProvinceCheck(ts, type) {
            $(ts).parent().find(".region").removeClass("regionerror");
            $("#province-main").find(".prover").removeClass("cur");
            var _this = $(ts);
            var region = _this.parents(".reltab-formbox").find(".region");
            if (type == 2) {
                $(".search-bar").empty();

                if (region.attr("pval").length > 0) {
                    var regarr = region.attr("pval").split("、");
                    var regarrtxt = region.text().split("、");
                    for (var i = 0; i < regarr.length; i++) {
                        $(".search-bar").append('<span code="' + regarr[i] + '">' + regarrtxt[i] + '</span>');
                    }
                }
                setNationCode();
                layer.open({
                    type: 1,
                    skin: 'layui-layer-province', //样式类名
                    title: "选择地区/国家",
                    btn: ['确定', '取消'],
                    area: ['500px', 'auto'], //宽高
                    content: $("#country_code_layer"),
                    yes: function (index, layero) {
                        var pstr = "";
                        var pval = "";
                        layero.find(".search-bar span").each(function () {
                            pstr += $(this).text() + "、";
                            pval += $(this).attr("code") + "、";
                        });
                        if (pstr) {
                            pstr = pstr.substring(0, pstr.length - 1);
                            pval = pval.substring(0, pval.length - 1);
                            region.text(pstr);
                        } else {
                            region.text("未选择任何区域");
                        }
                        region.siblings(".provinceId").val(pval);
                        region.siblings(".provinceText").val(pstr);
                        region.attr("pval", pval);
                        layer.close(index);
                    }
                })
            } else {
                if (region.text().length > 0) {
                    var regarr = region.attr("pval").split("、");
                    for (var i = 0; i < regarr.length; i++) {
                        $("[pval='" + regarr[i] + "']").addClass("cur");
                    }
                }
                layer.open({
                    type: 1,
                    skin: 'layui-layer-province', //样式类名
                    title: '选择省份',
                    btn: ['确定', '取消'],
                    area: ['500px', 'auto'], //宽高
                    content: $("#province-main"),
                    yes: function (index, layero) {
                        var pstr = "";
                        var pval = "";
                        layero.find("a.cur").each(function () {
                            pstr += $(this).text() + "、";
                            pval += $(this).attr("pval") + "、";
                        });
                        if (pstr) {
                            pstr = pstr.substring(0, pstr.length - 1);
                            pval = pval.substring(0, pval.length - 1);
                            region.text(pstr);
                        } else {
                            region.text("未选择任何区域");
                        }

                        region.siblings(".provinceId").val(pval);
                        region.siblings(".provinceText").val(pstr);
                        region.attr("pval", pval);
                        layer.close(index);
                    }
                });
            }

        }
        //删除
        function deleteProvinceCheck(ts) {
            $(ts).parents(".reltab-formbox").remove();
        }
        //选择弹框省份
        $(".prover").on("click", function () {
            if ($(this).hasClass("cur")) {
                $(this).removeClass("cur");
            } else {
                $(this).addClass("cur");
            }
        });
        //上传
        uploadImage("uploadShowImageUrl", 1, "masonryShowImageUrl", false, "ShowImageUrl", 1, false, 480, 640, "ShowImages", 5, true);
        function UploadImg(id) {
            $('#' + id).trigger("click");
        }
        function setTitleImg(obj) {
            $(".thumbnail").find("input[name='Type']").val("1");
            $(".thumbnail").removeClass("img-selected");
            var input = $(obj).closest(".caption").find("input[name='Type']");
            input.val("3");
            $(obj).closest(".thumbnail").addClass("img-selected");
        }

        //国际
        setNationCode();
        //初始化国家和区号
        function setNationCode() {
            $("#szmList,#gjList").empty();
            $("#currentCapital").text(ncode[0].Initial);
            for (var i = 0; i < ncode.length; i++) {
                $("#szmList").append('<li onclick="szmLiCheck(this)"><a href="javascript:void(0)" class="' + (i == 0 ? "cur" : "") + '">' + ncode[i].Initial + '</a></li>');
            }
            publicGj(0);

        }
        function publicGj(indexs) {
            for (var j = 0; j < ncode[indexs].Data.length; j++) {
                $("#gjList").append('<li data-code="' + ncode[indexs].Data[j].PhoneCode + '" data-en="' + ncode[indexs].Data[j].CountryNameEN + '" data-zh="' + ncode[indexs].Data[j].CountryNameCN + '" onclick="gjLiCheck(this)"><span>' + ncode[indexs].Data[j].CountryNameCN + '</span></li>');
                var list = $(".search-bar").find("span");
                for (var k = 0; k < list.length; k++) {
                    if (ncode[indexs].Data[j].PhoneCode == $(list[k]).attr("code")) {
                        $('li[data-code="' + ncode[indexs].Data[j].PhoneCode + '"]').addClass('cur');
                    }
                }
            }
        }
        //点击首字母更换国家
        function szmLiCheck(ts) {
            var indexs = $(ts).index();
            $(ts).find("a").addClass("cur").end().siblings().find("a").removeClass('cur');
            $("#currentCapital").text($(ts).text());
            $("#gjList").empty();
            publicGj(indexs);
        }

        function gjLiCheck(ts) {
            if ($(ts).hasClass("cur")) {
                $(ts).removeClass("cur");
                $(".search-bar").find("span[code='" + $(ts).attr("data-code") + "']").remove();
            } else {
                $(ts).addClass("cur");
                $(".search-bar").append("<span code=" + $(ts).attr("data-code") + ">" + $(ts).attr("data-zh") + "</span>")
            }
        }
        //模拟单选
        function onchecked(ts) {
            $(ts).addClass("cur").siblings("li").removeClass("cur");
            var nval = $(ts).attr("nvalue");
            $(ts).parent().next(":hidden").val(nval);
            var pr = $(ts).parents('.formbox-rtextmrt').next().find('.Price');
            if (nval == "3") {
                pr.prop("readonly", false);
            } else {
                pr.prop("readonly", true).val("");
            }
        }
        //模拟单选初始化
        initchecked();
        function initchecked() {
            $("[initval]").each(function () {
                if ($(this).attr("initval")) {
                    $(this).find("[nvalue='" + $(this).attr("initval") + "']").addClass("cur");
                    var pr = $(this).parents('.formbox-rtextmrt').next().find('.Price');
                    if ($(this).attr("initval") == "3") {
                        pr.prop("readonly", false);
                    } else {
                        pr.prop("readonly", true).val("");
                    }
                }
            })
        }
        //监听一口价输入框改变判断自动拒绝和接受价格的变化
        if ($("[name='Price']").val()) {
            var prdom = $("[name='LPrice'],[name='HPrice']");
            prdom.attr("max", $("[name='Price']").val());
        }
        if ($("[name='LPrice']").val()) {
            var prdom = $("[name='HPrice']");
            prdom.attr("min", $("[name='LPrice']").val());
        }
        $("[name='Price']").on("input", function () {
            var prdom = $("[name='LPrice'],[name='HPrice']");
            if ($(this).val()) {
                prdom.attr("max", $(this).val());
            } else {
                prdom.attr("max", "9999999");
            }
        });

        $("[name='LPrice']").on("input", function () {
            var prdom = $("[name='HPrice']");
            if ($(this).val()) {
                prdom.attr("min", $(this).val());
            } else {
                prdom.attr("min", "1");
            }
        });
        $(".Price").on("input", function () {
            $(this).next("label").hide();
        })
        //$(".selectpicker").on("click", function () {
        //    $("#bootstraperror").remove();
        //})
        //返回html
        function regEditFreight(dm) {
            var ht = $(dm).html();
            return ht;
        }
        $('.selectpicker').selectpicker({
            'selectedText': 'cat',
            'noneSelectedText': '请选择'
        });

        $('[name="Price"]').blur(function () {
            $('[name="HPrice"],[name="LPrice"]').blur()
        })

    </script>
    <div id="dialog"></div>
</div>
<div class="login-tip">
</div>
<ul class="kt-backtop" id="roll_top" style="display:none;">
    <li>
        <span class="text-top">顶部</span><span class="bg-top"></span>
    </li>
</ul>

<script src="../static/js/public.js"></script>
<script src="http://www.cardhobby.com/Scripts/MinTalk.js"></script>

<script type="text/javascript">
    function getKey() {
        if (event.keyCode == 13) {
            Q();
            return false;
        }
    }

    function Q() {
        var key = $('#kword').val();
        var searchtype = $("[name='searchtype']").val();//1是市场，2是求卡
        if (searchtype == "1") {
            location.href = "/market/search?keyword=" + escape(key) + "&searchtype=" + searchtype;
        } else if (searchtype == "2") {
            location.href = "/market/searchbuy?keyword=" + escape(key) + "&searchtype=" + searchtype;
        } else {
            location.href = "/market/Official?keyword=" + escape(key) + "&searchtype=" + searchtype;
        }
    }


    //获取地址栏参数
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
    //修改搜索文本框值
    var kWord = unescape(GetQueryString('keyword'));
    if (kWord.length > 0 && kWord != 'null') {
        $('#kword').val(kWord);
    }
    //修改搜索类型
    var searchtype = unescape(GetQueryString('searchtype'));

    if (searchtype.length > 0 && searchtype != 'null') {
        $('#searchtype').val(searchtype);
        $('.searchBox_type').find('[stype="' + $('#searchtype').val() + '"]').addClass("active").siblings("[stype]").removeClass("active")
    }

    var url = window.location.href;
    console.log(url.indexOf("www"))
    if (url.indexOf("www") >= 0) {
        var _hmt = _hmt || [];
        (function () {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?3f3d88cf860b4002ad1defbdb604fa14";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    }

    $(function () {
        /*返回顶部*/
        $(window).scroll(function () {
            if ($(window).scrollTop() > 300) {
                $('#roll_top').fadeIn(400);//当滑动栏向下滑动时，按钮渐现的时间
            } else {
                $('#roll_top').fadeOut(0);//当页面回到顶部第一屏时，按钮渐隐的时间
            }
        });
        $('#roll_top').click(function () {
            $('html,body').animate({
                scrollTop: '0px'
            }, 300);//返回顶部所用的时间 返回顶部也可调用goto()函数
        });

        var cbox = $(".down_child_box");
        cbox.each(function () {
            var typeLen = $(this).find("ul").length;
            $(this).css("width", (typeLen * 140) + "px");
        })
    });

    function searchBoxType(ts) {
        $(ts).addClass("active").siblings("a").removeClass("active");//控制切换选中
        $("[name='searchtype']").val($(ts).attr("stype"));//赋值隐藏域
    }
    $(document).ready(function () {
        var url = window.location.href.toLowerCase();
        if (url.indexOf("www") >= 0) {
            var _hmt = _hmt || [];
            (function () {
                var hm = document.createElement("script");
                hm.src = "https://hm.baidu.com/hm.js?3f3d88cf860b4002ad1defbdb604fa14";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();
        }
    })

</script>
<script type="text/javascript">
    onGetTokenAccId('10442', '95e15fe5e8828c923fb6b19ede3fb890');
</script>
</body>
</html>