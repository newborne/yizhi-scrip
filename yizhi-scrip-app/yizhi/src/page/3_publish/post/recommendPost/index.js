import React, {Component} from 'react';

import {
  View,
  Text,
  FlatList,
  Image,
  Modal,
  TouchableOpacity,
} from 'react-native';
import IconFont from '@src/component/IconFont';
import ImageViewer from 'react-native-image-zoom-viewer';
import LinearGradient from 'react-native-linear-gradient';
import Validator from '@src/util/Validator';
import {EMOTIONS_DATA} from '@src/component/Emotion/datasource';
import Date from '@src/util/Date';
import {ActionSheet} from 'teaset';
import {pxToDp} from '@src/util/pxToDp';
class Index extends Component {
  params = {
    page: 1,
    pagesize: 3,
  };
  state = {
    list: [
      {
        age: 12,
        agediff: 0,
        comment_count: 12,
        title: '测试标题',
        content:
          '      人生，其实就是一次过程，很多事，很多人，失败过，经历过才会懂，才会成熟。当失败来临的时候，不要伤悲，而应该看作是一次成长的机会，一次锻炼的机会。冲过去，会更美好、更灿烂的生活等着你，更会有一番成就感；如果退而不前，那只能迎来更多的失败，更多人生的遗憾/{weixiao}',
        create_time: '2021-05-13T15:50:58.000Z',
        dist: 800,
        sex: '女',
        guid: '151321658241620920808616',
        logo: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
        images: [
          {
            thum_img_path: 'https://z3.ax1x.com/2021/05/26/2SOHRU.jpg',
          },
        ],
        like_count: 0,
        marry: '',
        mobile: '15132165824',
        userName: '孤独的小王子',
        star_count: 4,
        tid: 264,
        uid: 1913,
        edu: '六年级',
      },
      {
        age: 12,
        agediff: 0,
        comment_count: 0,
        title: '测试标题',
        content:
          '生活如酒，或芳香，或浓烈，因为诚实，它变得醇厚；生活如歌，或高昂，或低沉，因为守信，它变得悦耳；生活如画，或明丽，或素雅，因为诚信，它变得美丽。',
        create_time: '2021-05-16T13:55:06.000Z',
        dist: 1062,
        sex: 'man',
        guid: '132569874521621002050048',
        logo: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
        images: [
          {
            thum_img_path: 'https://z3.ax1x.com/2021/05/26/2SOHRU.jpg',
          },
        ],
        like_count: 0,
        marry: '',
        mobile: '13256987452',
        userName: '美丽的雅马哈',
        star_count: 0,
        tid: 292,
        uid: 1933,
        edu: '六年级',
      },
      {
        age: 12,
        agediff: 0,
        title: '测试标题',
        comment_count: 4,
        content:
          '流光容易把人抛，红了樱桃，绿了芭蕉。走在自己生命路上，有时很难看清自己是否走了弯路。不妨跳出来，调准焦距，才能照出最好生活。/{ziya}/{ziya}',
        create_time: '2021-05-14T11:37:53.000Z',
        dist: 466,
        sex: 'man',
        guid: '153285668741620992107584',
        logo: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
        images: [
          {
            thum_img_path: 'https://z3.ax1x.com/2021/05/26/2SOHRU.jpg',
          },
        ],
        like_count: 1,
        marry: '',
        mobile: '15328566874',
        userName: '奋斗的少年',
        star_count: 2,
        tid: 283,
        uid: 1925,
        edu: '六年级',
      },
    ],
    showAlbum: false,
    imgUrls: [],
    currentIndex: 0,
  };
  // componentDidMount() {
  //   this.getList();
  // }
  // 渲染富文本内容
  rendeRichText = text => {
    const list = Validator.renderRichText(text);
    return list.map((v, i) => {
      if (v.text) {
        return (
          <Text style={{color: '#666'}} key={i}>
            {v.text}
          </Text>
        );
      } else if (v.image) {
        return (
          <Image
            style={{width: pxToDp(25), height: pxToDp(25)}}
            key={i}
            source={EMOTIONS_DATA[v.image]}
          />
        );
      } else {
        return <></>;
      }
    });
  };
  // // 获取 推荐动态的数据
  // getList = async (isNew = false) => {
  //   const res = await request.privateGet(QZ_TJDT, this.params);
  //   console.log(res);
  //   if (isNew) {
  //     // 重置数据
  //     this.setState({list: res.data});
  //   } else {
  //     this.setState({list: [...this.state.list, ...res.data]});
  //   }
  //   this.totalPages = res.pages;
  //   this.isLoading = false;
  // };
  // 滚动条触底事件
  // onEndReached = () => {
  //   /*
  // 1 判断还有没有下一页数据
  // 2 节流阀
  //  */
  //   if (this.params.page >= this.totalPages || this.isLoading) {
  //     return;
  //   } else {
  //     // 还有下一页数据
  //     this.isLoading = true;
  //     this.params.page++;
  //     this.getList();
  //   }
  // };

  // // 点赞
  // handleStar = async item => {
  //   /*
  // 1 构造点赞参数 发送请求
  // 2 返回值里 提示 点赞成功还是取消点赞
  // 3 点赞成功 =>  通过极光 给发送一条消息 "xxx 点赞了你的动态"
  // 4 重新发送请求 获取 列表数据-> 渲染
  //  */
  //   const url = QZ_DT_DZ.replace(':id', item.tid);
  //   const res = await request.privateGet(url);
  //   console.log(res);
  //   // 点赞成功 还是 取消点赞
  //   if (res.data.iscancelstar) {
  //     // 取消点赞
  //     Toast.smile('取消成功');
  //   } else {
  //     // 点赞成功
  //     Toast.smile('点赞成功');

  //     const text = `${this.props.UserStore.user.userName} 点赞了你的动态`;
  //     const extras = {user: JSON.stringify(this.props.UserStore.user)};
  //     JMessage.sendTextMessage(item.guid, text, extras);
  //   }

  //   // 重新发送请求 获取数据
  //   // this.setState({ list: [] });
  //   this.params.page = 1;
  //   this.getList(true);
  // };

  // // 喜欢
  // handleLike = async item => {
  //   const url = QZ_DT_XH.replace(':id', item.tid);
  //   const res = await request.privateGet(url);
  //   // console.log(res);
  //   if (res.data.iscancelstar) {
  //     // 取消喜欢
  //     Toast.smile('取消喜欢');
  //   } else {
  //     // 喜欢成功
  //     Toast.smile('喜欢成功');
  //   }

  //   this.params.page = 1;
  //   this.getList(true);
  // };

  // 点击 更多 按钮
  handleMore = async item => {
    const opts = [
      {title: '举报', onPress: () => alert('举报')},
      {title: '不感兴趣', onPress: () => alert('不感兴趣')},
    ];
    ActionSheet.show(opts, {title: '取消'});
  };

  // // 不感兴趣 => 获取动态列表的时候 里面就不会出现当前用户
  // noInterest = async item => {
  //   const url = QZ_DT_BGXQ.replace(':id', item.tid);
  //   const res = await request.privateGet(url);

  //   Toast.smile('操作成功');

  //   this.params.page = 1;
  //   this.getList(true);
  // };

  // 点击相册图片放大
  handleShowAlbum = (index, ii) => {
    const imgUrls = this.state.list[index].images.map(v => ({
      url: v.thum_img_path,
    }));
    this.setState({imgUrls, currentIndex: ii, showAlbum: true});
  };

  // // 跳转到评论页面
  // goComment = item => {
  //   // this.props.navigation
  //   this.context.navigate('Comment', item);
  // };
  render() {
    const {list, imgUrls, currentIndex, showAlbum} = this.state;
    return (
      <>
        <FlatList
          style={{backgroundColor: '#f7f7f7', marginBottom: pxToDp(12)}}
          onEndReached={this.onEndReached}
          onEndReachedThreshold={0.1}
          data={list}
          keyExtractor={v => v.tid + ''}
          renderItem={({item, index}) => (
            <>
              <View
                key={index}
                style={{
                  padding: pxToDp(10),
                  borderBottomColor: '#ccc',
                  borderBottomWidth: pxToDp(1),
                }}>
                {/* 2.2.1 用户信息 开始 */}
                <View style={{flexDirection: 'row', alignItems: 'center'}}>
                  <View style={{paddingRight: pxToDp(15)}}>
                    <Image
                      style={{
                        width: pxToDp(40),
                        height: pxToDp(40),
                        borderRadius: pxToDp(20),
                      }}
                      source={{uri: item.logo}}
                    />
                  </View>

                  <View style={{flex: 2, justifyContent: 'space-around'}}>
                    <View style={{flexDirection: 'row', alignItems: 'center'}}>
                      <Text style={{color: '#555'}}>{item.userName}</Text>
                      <IconFont
                        style={{
                          marginLeft: pxToDp(5),
                          marginRight: pxToDp(5),
                          fontSize: pxToDp(18),
                          color: item.sex === 'woman' ? '#36cfc9' : '#37DC8A',
                        }}
                        name={
                          item.sex === 'woman' ? 'iconWomanMini' : 'iconManMini'
                        }
                      />
                      <Text style={{color: '#555'}}>{item.age}岁</Text>
                    </View>
                    <View style={{flexDirection: 'row'}}>
                      <Text style={{color: '#555', marginRight: pxToDp(5)}}>
                        {item.edu}
                      </Text>
                      <Text style={{color: '#555', marginRight: pxToDp(5)}}>
                        |
                      </Text>
                      <Text style={{color: '#555', marginRight: pxToDp(5)}}>
                        {item.agediff < 10 ? '年龄相近' : '有点代沟'}
                      </Text>
                    </View>
                  </View>
                  <View
                    style={{
                      flexDirection: 'row',
                      alignSelf: 'flex-end',
                      paddingTop: pxToDp(5),
                      paddingBottom: pxToDp(5),
                    }}>
                    <View>
                      <Text style={{color: '#666'}}>距离 {item.dist} m</Text>
                    </View>
                    <View>
                      <Text style={{color: '#666', marginLeft: pxToDp(8)}}>
                        {Date(item.create_time).fromNow()}
                      </Text>
                    </View>
                  </View>
                </View>
                {/* 2.2.1 用户信息 结束 */}

                {/* 2.3 动态内容 开始 */}
                <View>
                  <Text
                    style={{
                      alignSelf: 'center',
                      fontSize: pxToDp(18),
                      fontWeight: 'bold',
                      color: '#333',
                      marginTop: pxToDp(10),
                    }}>
                    {item.title}
                  </Text>
                </View>
                <View
                  style={{
                    marginTop: pxToDp(8),
                    flexDirection: 'row',
                    flexWrap: 'wrap',
                    alignItems: 'center',
                  }}>
                  {this.rendeRichText(item.content)}
                </View>
                {/* 2.3 动态内容 结束 */}
                {/* 2.4 相册 开始 */}
                <View
                  style={{
                    flexWrap: 'wrap',
                    flexDirection: 'row',
                    paddingTop: pxToDp(5),
                    paddingBottom: pxToDp(5),
                  }}>
                  {item.images.map((vv, ii) => (
                    <TouchableOpacity
                      onPress={() => this.handleShowAlbum(index, ii)}
                      key={ii}>
                      <Image
                        style={{
                          width: pxToDp(70),
                          height: pxToDp(70),
                          marginRight: pxToDp(5),
                        }}
                        source={{uri: vv.thum_img_path}}
                      />
                    </TouchableOpacity>
                  ))}
                </View>
                {/* 2.4 相册 结束 */}
                {/* 2.5 距离时间 开始 */}

                {/* 2.5 距离时间 结束 */}
                {/* 2.6 3个小图标 开始 */}
                <View
                  style={{
                    marginLeft: pxToDp(5),
                    marginRight: pxToDp(5),
                    flexDirection: 'row',
                    justifyContent: 'space-between',
                  }}>
                  <TouchableOpacity
                    style={{flexDirection: 'row', alignItems: 'center'}}
                    // onPress={this.handleStar.bind(this, item)}
                  >
                    <IconFont
                      style={{
                        fontSize: pxToDp(24),
                        marginLeft: pxToDp(5),
                        marginRight: pxToDp(5),
                        color: '#666',
                      }}
                      name="iconLove"
                    />
                    <Text style={{color: '#666'}}>{item.star_count}</Text>
                  </TouchableOpacity>
                  <TouchableOpacity
                    // onPress={this.goComment.bind(this, item)}
                    style={{flexDirection: 'row', alignItems: 'center'}}>
                    <IconFont
                      style={{
                        fontSize: pxToDp(24),
                        marginLeft: pxToDp(5),
                        marginRight: pxToDp(5),
                        color: '#666',
                      }}
                      name="iconComment"
                    />
                    <Text style={{color: '#666'}}>{item.comment_count}</Text>
                  </TouchableOpacity>
                  <TouchableOpacity
                    // onPress={this.handleLike.bind(this, item)}
                    style={{flexDirection: 'row', alignItems: 'center'}}>
                    <IconFont
                      style={{
                        fontSize: pxToDp(24),
                        marginLeft: pxToDp(5),
                        marginRight: pxToDp(5),
                        color: '#666',
                      }}
                      name="iconLike"
                    />
                    <Text style={{color: '#666'}}>{item.like_count}</Text>
                  </TouchableOpacity>
                </View>
                {/* 2.6 3个小图标 结束 */}
              </View>
              {this.params.page >= this.totalPages &&
              index === list.length - 1 ? (
                <View
                  style={{
                    height: pxToDp(30),
                    alignItems: 'center',
                    justifyContent: 'center',
                  }}>
                  <Text style={{color: '#666'}}>没有数据</Text>
                </View>
              ) : (
                <></>
              )}
            </>
          )}
        />
        <Modal visible={showAlbum} transparent={true}>
          <ImageViewer
            onClick={() => this.setState({showAlbum: false})}
            imageUrls={imgUrls}
            index={currentIndex}
          />
        </Modal>
      </>
    );
  }
}

export default Index;
