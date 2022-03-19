import React, {Component} from 'react';
import {Image, ScrollView, Text, TouchableOpacity, View} from 'react-native';
import GradientNavgation from '@src/component/GradientNavgation';
import Date from '@src/util/Date';
import {pxToDp} from '@src/util/pxToDp';

class Index extends Component {
  state = {
    list: [
      {
        news: {
          logo: 'https://p8.itc.cn/images01/20220319/ddc0fa5ebeae4e7085aec0baf95f01e1.png',
          title: '中国展示“大国应有的样子”',
          content:
            '国家主席习近平18日晚应约同美国总统拜登视频通话，两国元首就中美关系和乌克兰局势等共同关心的问题坦诚深入交换了意见。根据新华社发布的新闻稿，习近平在视频通话中指出，作为联合国安理会常任理事国和世界前两大经济体，我们不仅要引领中美关系沿着正确轨道向前发展，而且要承担应尽的国际责任，为世界的和平与安宁作出努力。多名接受《环球时报》记者采访的国际关系学者认为，中方在中美关系问题上坚持立场、划清“红线”，在乌克兰局势上基于全球利益劝和促谈，展示出“大国应有的样子”。',
          createTime: '2022-03-18T01:50:58.000Z',
        },
      },
      {
        news: {
          logo: 'https://pics4.baidu.com/feed/a2cc7cd98d1001e912872b9f825277e655e79748.png?token=c0cc0f65abd39a6e378000d049ce986e',
          title: '普京讲话画面突然被切换',
          content:
            '当地时间3月18日，俄罗斯总统普京在莫斯科卢日尼基体育场出席克里米亚并入俄罗斯8周年纪念活动并发表讲话。路透社描述称，活动中，俄罗斯国家电视台突然停止播放普京讲话画面，转而播放爱国歌曲。很快就有西方媒体开始暗示这“不寻常”，但克宫出面回应说，此次画面中断系出现技术故障。',
          createTime: '2022-03-18T01:50:58.000Z',
        },
      },
      {
        news: {
          logo: 'https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/baike/w%3D250/sign=d694f00fcfef76093c0b9e9a1edca301/35a85edf8db1cb139334c881d954564e93584bc7.jpg',
          title: '巴西家佣的“历史性时刻”',
          content:
            '巴西日前正式颁布扩大家庭佣人17项新权利的宪法修正案。包括合理控制工作时间、给予加班补偿费用、强制性工龄保证基金及失业保险等。法案首次赋予巴西家佣与其他工人一样的权利。巴西社会党参议员利迪策林感慨道：“这是一个历史性时刻。”            巴西许多中产阶级家庭都习惯聘请家佣。这些家庭通常有两道房门，一道是主人的，一道是佣人的，家佣一般不能从正门进。巴西是一个对劳工保护相对严格的国家，但家佣们却一直是被权益忽略的人群。他们在社会偏见和艰苦环境中承担着繁重的劳动，却难以享受劳动法的保护。',
          createTime: '2022-03-18T01:50:58.000Z',
        },
      },
      {
        news: {
          logo: 'http://www.chinapeace.gov.cn/data/attachement/jpg/site75/20130808/c89cdcf6a687136d2f9605.jpg',
          title: '清华城管',
          content:
            '25岁的清华大学生命学院毕业生韦伟，突然成了媒体热议的新闻人物。最近一个月内，韦伟先后参加了广西柳州市不同城区的两次城管部门招考，最终都以优异的成绩入选。“一纸文凭不过是代表自己的学习经历，并不能说明什么，从小事做起慢慢积累工作经验、人生阅历，当机会来到时就能稳稳抓住。”今年以来，各地城管相继出现的负面新闻，加上招工难、就业难等因素的作用，这一抉择在很多人看来似乎有点“疯狂”，有人由此感慨清华北大的“天之骄子”已经走下神坛，有人怀疑他是想借此炒作博出名，也有人将他的选择当作是就业难的一个例证……面对如潮质疑，小韦则拿着录用通知书淡然一笑:“再不疯狂我们就老了!”',
          createTime: '2022-03-18T01:50:58.000Z',
        },
      },
      {
        news: {
          logo: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2711286669,2057337904&fm=26&gp=0.jpg',
          title: '钟南山院士:一言九鼎，百姓安心。',
          content:
            '“有院士的专业，有战士的勇猛，更有国士的担当。”这是人民日报微博上对钟南山的评价。自古以来，我国知识分子就有“为天地立心，为生民立命，为往圣继绝学，为万世开太平”的志向和传统。钟南山院士胸怀大理想、心中有人民。2003年非典肆虐，67岁的他说“把最重的病人送到我这来。”而今，武汉疫情肆虐，84岁的他一边告诉公众“尽量不要去武汉”，- -边自己却登上了前往武汉的高铁。面对未知的艰难，他勇担大任，一往无前，既有医者仁心的专业技术，也有战士迎难而上的拼搏狠劲。但是在他看似无坚不推的外衣之下，却深藏- -颗柔情似水的仁人之心、一腔热血爱国的赤诚之情。',
          createTime: '2022-03-18T01:50:58.000Z',
        },
      },
      {
        news: {
          logo: 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1666429739,3757391271&fm=26&gp=0.jpg',
          title: '李兰娟院士:首提封城，深入“红区”',
          content:
            '封城，在中国的疾控史上，从未有过，连2003年非典时期也没有。万一疫情没什么大事，李兰娟一生的名誉和声望恐都将毁于一旦。她不是不知道事关重大，但在她心里，人民高于一切，生命重于泰山。有网友慨叹，钟南山、李兰娟是可以托付国运的大医，与其称之谓院士，不如誉之为国士，发大医国士之良知灼见，一能断论新型冠状病毒人传人；二敢第一个提出建议封城；三是亲赴第一线抗击病毒。鲁迅先生说过，我们从古以来，就有埋头苦干的人，有拼命硬干的人，有为民请命的人，有舍身求法的....这就是中国的脊梁。倘若先生在世，他必会赞誉这位绍兴老乡为巾帼英雄、新时代的民族脊梁。',
          createTime: '2022-03-18T01:50:58.000Z',
        },
      },
    ],
  };
  render() {
    const {list} = this.state;
    return (
      <>
        <GradientNavgation title="今日热点" />
        <ScrollView>
          <View>
            {list.map((v, i) => (
              <TouchableOpacity
                // onPress={() => this.context.navigate('Chat', v.news)}
                key={i}
                style={{
                  height: pxToDp(200),
                  flexDirection: 'row',
                  borderBottomWidth: pxToDp(1),
                  borderBottomColor: '#ccc',
                }}>
                <View
                  style={{
                    padding: pxToDp(15),
                    width: '60%',
                  }}>
                  <Text style={{color: '#666', fontWeight: 'bold'}}>
                    {v.news.title}
                  </Text>
                  <Text numberOfLines={7} style={{color: '#666'}}>
                    {v.news.content}
                  </Text>
                </View>
                <View
                  style={{
                    padding: pxToDp(15),
                    width: '40%',
                    alignItems: 'flex-end',
                  }}>
                  <Image
                    source={{uri: v.news.logo}}
                    style={{
                      width: pxToDp(136),
                      height: pxToDp(136),
                    }}
                  />
                  <View>
                    <Text style={{color: '#666', textAlign: 'right'}}>
                      {Date(v.news.createTime).format('YYYY-MM-DD HH:MM:SS')}
                    </Text>
                  </View>
                </View>
              </TouchableOpacity>
            ))}
          </View>
        </ScrollView>
      </>
    );
  }
}
export default Index;
