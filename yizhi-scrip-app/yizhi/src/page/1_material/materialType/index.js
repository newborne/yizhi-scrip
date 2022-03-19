import React, {Component} from 'react';
import {
  StatusBar,
  View,
  ScrollView,
  TouchableOpacity,
  Image,
  Text,
} from 'react-native';
import {BoxShadow} from 'react-native-shadow';
import {pxToDp} from '@src/util/pxToDp';
import GradientNavgation from '@src/component/GradientNavgation';
import SearchBox from '@src/component/SearchBox';
import TouchableScale from 'react-native-touchable-scale';
import Date from '@src/util/Date';
class Index extends Component {
  state = {
    list1: [
      {
        courseSort: {
          logo: 'https://z3.ax1x.com/2021/06/09/2y5BlV.png',
          title: '爱国',
          content: '/积累经典短句\n/表达深刻主题',
        },
      },
      {
        courseSort: {
          logo: 'https://z3.ax1x.com/2021/06/09/2y5BlV.png',
          title: '诚信',
          content: '/解读海内外时事热点\n/丰富议论文论据',
        },
      },
      {
        courseSort: {
          logo: 'https://z3.ax1x.com/2021/06/09/2y5BlV.png',
          title: '敬业',
          content: '/解读各领域优秀人物\n/丰富议论文依据',
        },
      },
      {
        courseSort: {
          logo: 'https://z3.ax1x.com/2021/06/09/2y5BlV.png',
          title: '友善',
          content: '/解读文学、影视经典\n/拓展视野',
        },
      },
      {
        courseSort: {
          logo: 'https://z3.ax1x.com/2021/06/09/2y5BlV.png',
          title: '豪放',
          content: '/唐宋八大家\n/初唐四杰',
        },
      },
      {
        courseSort: {
          logo: 'https://z3.ax1x.com/2021/06/09/2y5BlV.png',
          title: '婉约',
          content: '/唐宋八大家\n/初唐四杰',
        },
      },
      {
        courseSort: {
          logo: 'https://z3.ax1x.com/2021/06/09/2y5BlV.png',
          title: '经典短句',
          content: '/积累经典短句\n/表达深刻主题',
        },
      },
      {
        courseSort: {
          logo: 'https://z3.ax1x.com/2021/06/09/2y5BlV.png',
          title: '时事',
          content: '/解读海内外时事热点\n/丰富议论文论据',
        },
      },
      {
        courseSort: {
          logo: 'https://z3.ax1x.com/2021/06/09/2y5BlV.png',
          title: '人物',
          content: '/解读各领域优秀人物\n/丰富议论文依据',
        },
      },
      {
        courseSort: {
          logo: 'https://z3.ax1x.com/2021/06/09/2y5BlV.png',
          title: '名著影视',
          content: '/解读文学、影视经典\n/拓展视野',
        },
      },
      {
        courseSort: {
          logo: 'https://z3.ax1x.com/2021/06/09/2y5BlV.png',
          title: '诗词歌赋',
          content: '/唐宋八大家\n/初唐四杰',
        },
      },
    ],
    list2: [
      {
        news: {
          logo: '',
          title: '爱国',
          content: '捐躯赴国难，视死忽如归。 曹植 《白马篇》',
          createTime: '2022-03-18T01:50:58.000Z',
        },
      },
      {
        news: {
          logo: '',
          title: '爱国',
          content: '捐躯赴国难，视死忽如归。 曹植 《白马篇》',
          createTime: '2022-03-18T01:50:58.000Z',
        },
      },
      {
        news: {
          logo: '',
          title: '爱国',
          content: '捐躯赴国难，视死忽如归。 曹植 《白马篇》',
          createTime: '2022-03-18T01:50:58.000Z',
        },
      },
      {
        news: {
          logo: '',
          title: '爱国',
          content: '捐躯赴国难，视死忽如归。 曹植 《白马篇》',
          createTime: '2022-03-18T01:50:58.000Z',
        },
      },
      {
        news: {
          logo: '',
          title: '爱国',
          content: '捐躯赴国难，视死忽如归。 曹植 《白马篇》',
          createTime: '2022-03-18T01:50:58.000Z',
        },
      },
      {
        news: {
          logo: '',
          title: '爱国',
          content: '捐躯赴国难，视死忽如归。 曹植 《白马篇》',
          createTime: '2022-03-18T01:50:58.000Z',
        },
      },
      {
        news: {
          logo: '',
          title: '爱国',
          content: '捐躯赴国难，视死忽如归。 曹植 《白马篇》',
          createTime: '2022-03-18T01:50:58.000Z',
        },
      },
      {
        news: {
          logo: '',
          title: '爱国',
          content: '捐躯赴国难，视死忽如归。 曹植 《白马篇》',
          createTime: '2022-03-18T01:50:58.000Z',
        },
      },
      {
        news: {
          logo: '',
          title: '爱国',
          content: '捐躯赴国难，视死忽如归。 曹植 《白马篇》',
          createTime: '2022-03-18T01:50:58.000Z',
        },
      },
    ],
  };
  render() {
    const {list1, list2} = this.state;
    return (
      <>
        <GradientNavgation title="分类素材" />
        <View style={{paddingTop: pxToDp(10), backgroundColor: '#f7f7f7'}}>
          <SearchBox
            onChangeText={txt => this.setState({txt})}
            value={this.state.txt}
          />
        </View>
        <ScrollView
          style={{height: pxToDp(170), backgroundColor: '#f7f7f7'}}
          horizontal={true} //水平滚动开启
          showsHorizontalScrollIndicator={false} //是否显示滚动条
        >
          <View
            style={{
              flexDirection: 'row',
              flexWrap: 'wrap',
              justifyContent: 'space-between',
              padding: pxToDp(12),
            }}>
            {list1.map((v, i) => (
              <BoxShadow
                setting={{
                  width: pxToDp(80),
                  height: pxToDp(80),
                  color: '#000',
                  border: pxToDp(1),
                  radius: pxToDp(12),
                  opacity: 0.1,
                  x: 2,
                  y: 2,
                  style: {
                    justifyContent: 'center',
                    margin: pxToDp(8),
                    flexDirection: 'row',
                  },
                }}>
                <TouchableScale
                  key={i}
                  style={{
                    height: pxToDp(80),
                    width: pxToDp(80),
                    backgroundColor: '#ffffff',
                    borderRadius: pxToDp(12),
                  }}>
                  <View>
                    <Text
                      style={{
                        color: '#666',
                        fontWeight: 'bold',
                        fontSize: pxToDp(16),
                        marginLeft: pxToDp(10),
                        marginTop: pxToDp(20),
                      }}>
                      {v.courseSort.title}
                    </Text>
                    <Text
                      numberOfLines={2}
                      style={{
                        color: '#666',
                        fontSize: pxToDp(7),
                        marginTop: pxToDp(5),
                        marginLeft: pxToDp(10),
                      }}>
                      {v.courseSort.content}
                    </Text>
                  </View>
                  <Image
                    source={{uri: v.courseSort.logo}}
                    style={{
                      width: pxToDp(24),
                      height: pxToDp(24),
                      position: 'absolute',
                      borderRadius: pxToDp(12),
                      right: pxToDp(0),
                      top: pxToDp(1),
                    }}
                  />
                </TouchableScale>
              </BoxShadow>
            ))}
          </View>
        </ScrollView>

        <ScrollView>
          <View style={{marginBottom: pxToDp(12)}}>
            {list2.map((v, i) => (
              <TouchableOpacity
                // onPress={() => this.context.navigate('Chat', v.news)}
                key={i}
                style={{
                  height: pxToDp(100),
                  flexDirection: 'row',
                  borderBottomWidth: pxToDp(1),
                  borderBottomColor: '#ccc',
                }}>
                <View
                  style={{
                    padding: pxToDp(15),
                    width: '60%',
                  }}>
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
                  <View>
                    <Text style={{color: '#666', fontWeight: 'bold'}}>
                      {v.news.title}
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
