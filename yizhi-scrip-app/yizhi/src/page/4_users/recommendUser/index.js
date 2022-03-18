import React, {Component} from 'react';
import {View, StatusBar, Text, Image, ScrollView} from 'react-native';
import {pxToDp} from '@src/util/pxToDp';
import RecommendUserHead from '@src/component/RecommendUserHead';
import TodayBest from '@src/component/TodayBest';
import IconFont from '@src/component/IconFont';
import TouchableScale from 'react-native-touchable-scale';
class Index extends Component {
  state = {
    // 接口要的数据
    params: {
      page: 1,
      pagesize: 10,
      sex: '',
      lastLogin: '',
      city: '',
      edu: '',
    },
    // 推荐朋友 数组
    recommendUserInfo: [
      {
        id: 33,
        logo: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
        userName: 'yizhiuser_33',
        sex: 'man',
        age: 8,
        tags: ['六年级，年龄相近'],
        similarity: 96,
      },
      {
        id: 84,
        logo: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
        userName: 'yizhiuser_84',
        sex: 'man',
        age: 17,
        tags: ['六年级，年龄相近'],
        similarity: 96,
      },
      {
        id: 36,
        logo: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
        userName: 'yizhiuser_36',
        sex: 'man',
        age: 6,
        tags: ['六年级，年龄相近'],
        similarity: 95,
      },
      {
        id: 83,
        logo: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
        userName: 'yizhiuser_83',
        sex: 'man',
        age: 8,
        tags: ['六年级，年龄相近'],
        similarity: 95,
      },
      {
        id: 79,
        logo: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
        userName: 'yizhiuser_79',
        sex: 'man',
        age: 12,
        tags: ['六年级，年龄相近'],
        similarity: 94,
      },
      {
        id: 54,
        logo: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
        userName: 'yizhiuser_54',
        sex: 'man',
        age: 11,
        tags: ['六年级，年龄相近'],
        similarity: 93,
      },
      {
        id: 15,
        logo: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
        userName: 'yizhiuser_15',
        sex: 'man',
        age: 16,
        tags: ['六年级，年龄相近'],
        similarity: 92,
      },
      {
        id: 34,
        logo: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
        userName: 'yizhiuser_34',
        sex: 'man',
        age: 14,
        tags: ['六年级，年龄相近'],
        similarity: 92,
      },
      {
        id: 74,
        logo: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
        userName: 'yizhiuser_74',
        sex: 'man',
        age: 16,
        tags: ['六年级，年龄相近'],
        similarity: 92,
      },
      {
        id: 76,
        logo: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
        userName: 'yizhiuser_76',
        sex: 'man',
        age: 14,
        tags: ['六年级，年龄相近'],
        similarity: 92,
      },
    ],
  };
  // componentDidMount() {
  //   this.getRecommendUserInfo();
  // }

  // 获取推荐朋友
  // getRecommendUserInfo = async () => {
  //   const res = await request.privateGet(FRIENDS_RECOMMEND, {
  //     ...this.state.params,
  //   });
  //   console.log(res.items);
  //   this.setState({recommendUserInfo: res.items});
  // };
  render() {
    const {recommendUserInfo} = this.state;
    return (
      <ScrollView>
        <View style={{backgroundColor: '#eee'}}>
          <View
            style={{
              height: pxToDp(128),
              justifyContent: 'center',
              alignItems: 'center',
              backgroundColor: '#fff',
            }}>
            <RecommendUserHead />
          </View>
          {/* 2.2 标题 开始 */}
          <View
            style={{
              height: pxToDp(48),
              flexDirection: 'row',
              justifyContent: 'center',
              alignItems: 'center',
              backgroundColor: '#ffffff',
              marginTop: pxToDp(4),
              marginBottom: pxToDp(4),
            }}>
            <Text style={{color: '#333', fontSize: pxToDp(24)}}>今日最佳</Text>
          </View>
          {/* 2.0 今日最佳推荐 开始 */}
          <TouchableScale
            style={{
              marginLeft: pxToDp(12),
              marginRight: pxToDp(12),
            }}>
            <TodayBest />
          </TouchableScale>
          {/* 2.0 今日最佳推荐 结束 */}
          {/* 2.1 推荐 开始 */}
          <View>
            {/* 2.2 标题 开始 */}
            <View
              style={{
                height: pxToDp(48),
                flexDirection: 'row',
                justifyContent: 'center',
                alignItems: 'center',
                backgroundColor: '#ffffff',
                marginTop: pxToDp(4),
                marginBottom: pxToDp(4),
              }}>
              <Text style={{color: '#333', fontSize: pxToDp(24)}}>推荐</Text>
              <TouchableScale
                style={{
                  position: 'absolute',
                  right: pxToDp(10),
                }}>
                <IconFont
                  style={{
                    color: '#333',
                    fontSize: pxToDp(24),
                  }}
                  name="iconFilter"
                />
              </TouchableScale>
            </View>
            {/* 2.2 标题 结束 */}
            {/* 2.3 列表内容 开始 */}
            <View style={{paddingLeft: pxToDp(4), paddingRight: pxToDp(4)}}>
              {recommendUserInfo.map((v, i) => (
                <TouchableScale
                  key={i}
                  // onPress={() => this.context.navigate('Detail', {id: v.id})}
                  style={{
                    flexDirection: 'row',
                    paddingTop: pxToDp(15),
                    paddingBottom: pxToDp(15),
                    backgroundColor: '#ffffff',
                    borderRadius: pxToDp(18),
                    marginBottom: pxToDp(4),
                    marginLeft: pxToDp(4),
                    marginRight: pxToDp(4),
                  }}>
                  {/* 图片 */}
                  <View
                    style={{
                      paddingLeft: pxToDp(15),
                      paddingRight: pxToDp(15),
                    }}>
                    <Image
                      style={{
                        width: pxToDp(56),
                        height: pxToDp(56),
                        borderRadius: pxToDp(28),
                      }}
                      source={{uri: v.logo}}
                    />
                  </View>
                  {/* 名称 */}
                  <View style={{flex: 2, justifyContent: 'space-around'}}>
                    <View style={{flexDirection: 'row', alignItems: 'center'}}>
                      <Text style={{color: '#666'}}>{v.userName} </Text>
                      <IconFont
                        style={{
                          marginLeft: pxToDp(5),
                          marginRight: pxToDp(5),
                          fontSize: pxToDp(18),
                          color: v.sex === 'woman' ? '#36cfc9' : '#37DC8A',
                        }}
                        name={
                          v.sex === 'woman' ? 'iconWomanMini' : 'iconManMini'
                        }
                      />
                      <Text style={{color: '#666'}}>{v.age}岁</Text>
                    </View>
                    <View style={{flexDirection: 'row'}}>
                      <Text style={{color: '#999'}}>{v.tags}</Text>
                    </View>
                  </View>
                  {/* 相似度 */}
                  <View
                    style={{
                      flexDirection: 'row',
                      alignItems: 'center',
                      width: pxToDp(100),
                      justifyContent: 'center',
                    }}>
                    <IconFont
                      name="iconSimilarity"
                      style={{color: '#37DC8A', fontSize: pxToDp(48)}}
                    />
                    <Text
                      style={{
                        position: 'absolute',
                        color: '#fff',
                        fontSize: pxToDp(18),
                      }}>
                      {v.similarity}
                    </Text>
                  </View>
                </TouchableScale>
              ))}
            </View>
            {/* 2.3 列表内容 结束 */}
          </View>
          {/* 2.1 推荐 结束 */}
        </View>
      </ScrollView>
    );
  }
}

export default Index;
