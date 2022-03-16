import React, {Component} from 'react';
import {View, Text, TouchableOpacity, Image} from 'react-native';
import {pxToDp} from '@src/util/pxToDp';
import IconFont from '@src/component/IconFont';
import Date from '@src/util/Date';
class Index extends Component {
  state = {
    list: [
      {
        user: {
          logo: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
          userName: '孤独的小王子',
        },
        latestMessage: {
          text: '最好的年华',
          createTime: '2021-05-13T15:50:58.000Z',
        },
        unreadCount: 5,
      },
    ],
  };
  // componentDidMount() {
  //   this.getConversations();
  // }

  // getConversations = async () => {
  //   const res = await JMessage.getConversations();
  //   if (res.length) {
  //     const idArr = res.map(v => v.target.username);
  //     const url = FRIENDS_PERSONALINFO_GUID.replace(':ids', idArr.join(','));
  //     const users = await request.privateGet(url);
  //     this.setState({list: res.map((v, i) => ({...v, user: users.data[i]}))});
  //   }
  // };

  render() {
    const {list} = this.state;
    return (
      <View style={{flex: 1, backgroundColor: '#f7f7f7'}}>
        <View
          style={{
            height: pxToDp(128),
            backgroundColor: '#fff',
            flexDirection: 'row',
            alignItems: 'center',
            paddingLeft: pxToDp(30),
            paddingRight: pxToDp(30),
            justifyContent: 'space-between',
          }}>
          <TouchableOpacity style={{alignItems: 'center'}}>
            <View
              style={{
                width: pxToDp(60),
                height: pxToDp(60),
                borderRadius: pxToDp(30),
                backgroundColor: '#ebc969',
                alignItems: 'center',
                justifyContent: 'center',
              }}>
              <IconFont
                name="iconAnnouncement"
                style={{fontSize: pxToDp(32), color: '#f7f7f7'}}
              />
            </View>
            <Text style={{color: '#666'}}>公告</Text>
          </TouchableOpacity>
          <TouchableOpacity style={{alignItems: 'center'}}>
            <View
              style={{
                width: pxToDp(60),
                height: pxToDp(60),
                borderRadius: pxToDp(30),
                backgroundColor: '#ff5314',
                alignItems: 'center',
                justifyContent: 'center',
              }}>
              <IconFont
                style={{color: '#f7f7f7', fontSize: pxToDp(32)}}
                name="iconLike"
              />
            </View>
            <Text style={{color: '#666'}}>点赞</Text>
          </TouchableOpacity>
          <TouchableOpacity style={{alignItems: 'center'}}>
            <View
              style={{
                width: pxToDp(60),
                height: pxToDp(60),
                borderRadius: pxToDp(30),
                backgroundColor: '#2fb4f9',
                alignItems: 'center',
                justifyContent: 'center',
              }}>
              <IconFont
                style={{color: '#f7f7f7', fontSize: pxToDp(32)}}
                name="iconComment"
              />
            </View>
            <Text style={{color: '#666'}}>评论</Text>
          </TouchableOpacity>
          <TouchableOpacity style={{alignItems: 'center'}}>
            <View
              style={{
                width: pxToDp(60),
                height: pxToDp(60),
                borderRadius: pxToDp(30),
                backgroundColor: '#1adbde',
                alignItems: 'center',
                justifyContent: 'center',
              }}>
              <IconFont
                style={{color: '#f7f7f7', fontSize: pxToDp(32)}}
                name="iconLove"
              />
            </View>
            <Text style={{color: '#666'}}>喜欢</Text>
          </TouchableOpacity>
        </View>
        <View>
          {list.map((v, i) => (
            <TouchableOpacity
              key={i}
              style={{
                padding: pxToDp(15),
                flexDirection: 'row',
                borderBottomWidth: pxToDp(1),
                borderBottomColor: '#ccc',
              }}>
              <View>
                <Image
                  source={{uri: v.user.logo}}
                  style={{
                    width: pxToDp(54),
                    height: pxToDp(54),
                    borderRadius: pxToDp(27),
                  }}
                />
              </View>
              <View
                style={{
                  justifyContent: 'space-evenly',
                  paddingLeft: pxToDp(15),
                }}>
                <Text style={{color: '#666'}}>{v.user.userName}</Text>
                <Text style={{color: '#666'}}>{v.latestMessage.text}</Text>
              </View>
              <View
                style={{
                  justifyContent: 'space-evenly',
                  flex: 1,
                  alignItems: 'flex-end',
                }}>
                <Text style={{color: '#666'}}>
                  {Date(v.latestMessage.createTime).fromNow()}
                </Text>
                <View
                  style={{
                    width: pxToDp(20),
                    height: pxToDp(20),
                    borderRadius: pxToDp(10),
                    backgroundColor: 'red',
                    alignItems: 'center',
                    justifyContent: 'center',
                  }}>
                  <Text style={{color: '#f7f7f7'}}>{v.unreadCount}</Text>
                </View>
              </View>
            </TouchableOpacity>
          ))}
        </View>
      </View>
    );
  }
}

export default Index;
