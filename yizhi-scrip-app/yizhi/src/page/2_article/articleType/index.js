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
import SearchBox from '@src/component/SearchBox';
class Index extends Component {
  state = {
    list: [
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
  };
  render() {
    const {list} = this.state;
    return (
      <ScrollView>
        <StatusBar backgroundColor="transparent" translucent={true} />
        <View style={{backgroundColor: '#f7f7f7'}}>
          <View style={{marginTop: pxToDp(10)}}>
            <SearchBox
              onChangeText={txt => this.setState({txt})}
              value={this.state.txt}
            />
          </View>
          <View
            style={{
              flexDirection: 'row',
              flexWrap: 'wrap',
              justifyContent: 'space-between',
              padding: pxToDp(12),
            }}>
            {list.map((v, i) => (
              <BoxShadow
                setting={{
                  width: pxToDp(164),
                  height: pxToDp(164),
                  color: '#000',
                  border: pxToDp(1),
                  radius: pxToDp(12),
                  opacity: 0.1,
                  x: 2,
                  y: 2,
                  style: {
                    justifyContent: 'center',
                    margin: pxToDp(14),
                    flexDirection: 'row',
                  },
                }}>
                <TouchableOpacity
                  key={i}
                  style={{
                    height: pxToDp(164),
                    width: pxToDp(164),
                    backgroundColor: '#ffffff',
                    borderRadius: pxToDp(12),
                  }}>
                  <View>
                    <Text
                      style={{
                        color: '#666',
                        fontWeight: 'bold',
                        fontSize: pxToDp(20),
                        margin: pxToDp(10),
                      }}>
                      {v.courseSort.title}
                    </Text>
                    <Text
                      numberOfLines={2}
                      style={{
                        color: '#666',
                        margin: pxToDp(14),
                        fontSize: pxToDp(12),
                      }}>
                      {v.courseSort.content}
                    </Text>
                  </View>
                  <Image
                    source={{uri: v.courseSort.logo}}
                    style={{
                      width: pxToDp(48),
                      height: pxToDp(48),
                      position: 'absolute',
                      borderRadius: pxToDp(48),
                      right: pxToDp(10),
                      bottom: pxToDp(10),
                    }}
                  />
                </TouchableOpacity>
              </BoxShadow>
            ))}
          </View>
        </View>
      </ScrollView>
    );
  }
}

export default Index;
