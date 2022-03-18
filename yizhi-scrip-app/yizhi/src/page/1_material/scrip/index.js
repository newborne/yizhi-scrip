import React, {Component} from 'react';
import {
  TouchableOpacity,
  View,
  Text,
  ImageBackground,
  StyleSheet,
  Image,
} from 'react-native';
import GradientNavgation from '@src/component/GradientNavgation';
import Swiper from 'react-native-deck-swiper';
import Request from '@src/util/Request';
import {MATERIAL_RECOMMEND_V2, BASE_URI, MATERIAL_ID_LOVE} from '@src/util/Api';
import IconFont from '@src/component/IconFont';
import {pxToDp} from '@src/util/pxToDp';
import {Toast} from 'teaset';
import TouchableScale from 'react-native-touchable-scale';
class Index extends Component {
  params = {
    page: 1,
    pagesize: 5,
  };
  // 总页数
  totalPages = 5;
  state = {
    // 当前被操作的数组的索引
    currentIndex: 0,
    cards: [
      {
        id: 8,
        header: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
        nick_name: '天下兴亡匹夫有责',
        age: '------',
        gender: '议论文',
        marry: '爱国',
        xueli: '豪放',
        dist: 0,
      },
      {
        id: 8,
        header: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
        nick_name: '雾霭朦胧',
        age: 21,
        gender: '女',
        marry: '未婚',
        xueli: '大专',
        dist: 0,
      },
      {
        id: 8,
        header: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
        nick_name: '雾霭朦胧',
        age: 21,
        gender: '女',
        marry: '未婚',
        xueli: '大专',
        dist: 0,
      },
      {
        id: 8,
        header: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
        nick_name: '雾霭朦胧',
        age: 21,
        gender: '女',
        marry: '未婚',
        xueli: '大专',
        dist: 0,
      },
    ],
  };

  constructor() {
    super();
    this.swiperRef = React.createRef();
  }
  componentDidMount() {
    // this.getFriendsCards();
  }

  // 获取要渲染的数据
  getFriendsCards = async () => {
    const res = await Request.privateGet(MATERIAL_RECOMMEND_V2, this.params);
    this.totalPages = res.pages;
    this.setState({cards: [...this.state.cards, ...res.data]});
  };

  // 设置用户喜欢或者不喜欢
  setLike = async type => {
    /*
    1 如何通过js的方式来swiper滑动
      swiper的Ref 来实现 获取到swiper的ref => swipeLeft()
    2 根据滑动方向或者 参数 来构造数据 将他们发送到后台
      1 先知道当前被操作的数组的元素-索引
     */

    //  this.swiperRef.swipeLeft();
    //  this.swiperRef.swipeRight();
    // console.log(this.state.currentIndex);
    this.sendLike(type);
    if (type === 'dislike') {
      this.swiperRef.swipeLeft();
    } else {
      this.swiperRef.swipeRight();
    }
  };

  // 发送喜欢或者不喜欢
  sendLike = async type => {
    const id = this.state.cards[this.state.currentIndex].id;
    const url = MATERIAL_ID_LOVE.replace(':id', id).replace(':type', type);
    const res = await Request.privateGet(url);
    Toast.message(res.data, 1000, 'center');
  };

  // 图片滑动完毕就会触发
  onSwipedAll = () => {
    /*
    1 一定有下一页的数据 ?
    2 简单的判断即可
     */
    if (this.params.page >= this.totalPages) {
      Toast.message('没有下一页数据', 1000, 'center');
      return;
    } else {
      this.params.page++;
      this.getFriendsCards();
    }
  };
  render() {
    const {cards, currentIndex} = this.state;
    // if (!cards[currentIndex]) {
    //   return <></>
    // }
    return (
      <View style={{flex: 1, backgroundColor: '#fff'}}>
        <GradientNavgation title="易知纸条" />
        <View style={{height: '60%', marginTop: '20%'}}>
          {cards[currentIndex] ? (
            <Swiper
              key={Date.now()}
              ref={ref => (this.swiperRef = ref)}
              cards={cards}
              renderCard={card => {
                return (
                  <View style={styles.card}>
                    <Image
                      source={require('@src/res/image/yizhi.png')}
                      style={{width: pxToDp(72), height: pxToDp(72)}}
                    />
                    <View
                      style={{
                        flex: 1,
                        alignItems: 'center',
                        justifyContent: 'space-around',
                      }}>
                      <View
                        style={{flexDirection: 'row', alignItems: 'center'}}>
                        <Text style={{color: '#555'}}>{card.nick_name}</Text>
                        <IconFont
                          style={{
                            fontSize: pxToDp(18),
                            color: card.gender === '女' ? '#b564bf' : 'red',
                          }}
                          name={
                            card.gender === '女'
                              ? 'icontanhuanv'
                              : 'icontanhuanan'
                          }
                        />
                        <Text style={{color: '#555'}}>{card.age}岁</Text>
                      </View>
                      <View style={{flexDirection: 'row'}}>
                        <Text style={{color: '#555'}}>{card.marry}</Text>
                        <Text style={{color: '#555'}}>|</Text>
                        <Text style={{color: '#555'}}>{card.xueli}</Text>
                        <Text style={{color: '#555'}}>|</Text>
                        <Text style={{color: '#555'}}>
                          {card.agediff < 10 ? '年龄相仿' : '有点代沟'}
                        </Text>
                      </View>
                    </View>
                  </View>
                );
              }}
              onSwiped={() => {
                this.setState({currentIndex: this.state.currentIndex + 1});
              }}
              // onSwipedAll={this.onSwipedAll}
              // onSwipedLeft={this.sendLike.bind(this, 'dislike')}
              // onSwipedRight={this.sendLike.bind(this, 'like')}
              cardIndex={currentIndex}
              backgroundColor={'transparent'}
              cardVerticalMargin={0}
              stackSize={3}
            />
          ) : (
            <></>
          )}
        </View>

        {/* 两个小图标 */}
        <View
          style={{
            flexDirection: 'row',
            justifyContent: 'space-between',
            width: '60%',
            alignSelf: 'center',
          }}>
          <TouchableScale
            // onPress={this.setLike.bind(this, 'dislike')}
            style={{
              backgroundColor: '#999',
              width: pxToDp(60),
              height: pxToDp(60),
              borderRadius: pxToDp(30),
              alignItems: 'center',
              justifyContent: 'center',
            }}>
            <IconFont
              style={{fontSize: pxToDp(30), color: '#fff'}}
              name="iconUnLove"
            />
          </TouchableScale>
          <TouchableScale
            // onPress={this.setLike.bind(this, 'like')}
            style={{
              backgroundColor: '#ff5314',
              width: pxToDp(60),
              height: pxToDp(60),
              borderRadius: pxToDp(30),
              alignItems: 'center',
              justifyContent: 'center',
            }}>
            <IconFont
              style={{fontSize: pxToDp(30), color: '#fff'}}
              name="iconLove"
            />
          </TouchableScale>
        </View>
      </View>
    );
  }
}
const styles = StyleSheet.create({
  card: {
    height: '50%',
    borderRadius: 24,
    borderWidth: 2,
    borderColor: '#eee',
    backgroundColor: '#fff',
  },
  text: {
    textAlign: 'center',
    fontSize: 50,
    backgroundColor: 'transparent',
  },
});

export default Index;
