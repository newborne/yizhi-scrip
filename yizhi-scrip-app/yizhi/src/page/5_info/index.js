import React, {Component} from 'react';
import {View, Text, StatusBar, TouchableOpacity, Image} from 'react-native';
import {NavigationContext} from '@react-navigation/native';
import {ListItem} from 'react-native-elements';
import LinearGradient from 'react-native-linear-gradient';
import IconFont from '@src/component/IconFont';
import {pxToDp} from '@src/util/pxToDp';
class Index extends Component {
  static contextType = NavigationContext;
  state = {
    user: {
      age: 12,
      sex: 'man',
      logo: 'https://z3.ax1x.com/2021/05/22/gqLnWq.png',
      userName: '奋斗的少年',
    },
    city: '上海市',
    // 粉丝的数量
    fanCount: 9,
    // 喜欢的数量
    loveCount: 8,
    // 相互关注的数量
    eachLoveCount: 5,
    // 控制 加载中的组件的切换显示
    refreshing: false,
  };
  render() {
    const {user, city, fanCount, loveCount, eachLoveCount, refreshing} =
      this.state;
    return (
      <View style={{flex: 1, backgroundColor: '#f7f7f7'}}>
        <StatusBar backgroundColor="transparent" translucent={true} />
        <LinearGradient
          style={{height: pxToDp(300), position: 'relative'}}
          colors={['#39DBD5', '#37DC8A']}
          start={{x: 0, y: 0}}
          end={{x: 1, y: 1}}>
          <TouchableOpacity
            onPress={() => this.context.navigate('EditUserInfo')}
            style={{position: 'absolute', top: pxToDp(60), right: pxToDp(30)}}>
            <IconFont
              name="iconEdit"
              style={{
                color: '#fff',
                fontSize: pxToDp(24),
              }}
            />
          </TouchableOpacity>

          <TouchableOpacity
            style={{
              flexDirection: 'column',
              paddingTop: pxToDp(15),
              paddingBottom: pxToDp(15),
              marginTop: pxToDp(50),
              alignSelf: 'center',
              alignItems: 'center',
              width: '50%',
              height: pxToDp(200),
            }}>
            {/* 图片 */}
            <View style={{padding: pxToDp(15)}}>
              <Image
                style={{
                  width: pxToDp(100),
                  height: pxToDp(100),
                  borderRadius: pxToDp(50),
                }}
                source={{uri: user.logo}}
              />
            </View>
            {/* 名称 */}
            <View style={{justifyContent: 'space-around'}}>
              <View
                style={{
                  flexDirection: 'row',
                  alignItems: 'center',
                  alignSelf: 'center',
                  marginBottom: pxToDp(30),
                }}>
                <Text
                  style={{
                    color: '#fff',
                    fontWeight: 'bold',
                    fontSize: pxToDp(17),
                  }}>
                  {user.userName}
                </Text>
              </View>
              <View style={{flexDirection: 'row'}}>
                <View
                  style={{
                    flexDirection: 'row',
                    backgroundColor: '#fff',
                    borderRadius: pxToDp(10),
                    paddingLeft: pxToDp(3),
                    paddingRight: pxToDp(3),
                    alignItems: 'center',
                    alignSelf: 'center',
                    margin: pxToDp(5),
                  }}>
                  <IconFont
                    style={{
                      fontSize: pxToDp(18),
                      marginLeft: pxToDp(5),
                      marginRight: pxToDp(5),
                      color: user.sex === 'woman' ? '#36cfc9' : '#37DC8A',
                    }}
                    name={user.sex === 'woman' ? 'iconWoman' : 'iconMan'}
                  />
                  <Text style={{color: '#555'}}>{user.age}岁</Text>
                </View>
                <View
                  style={{
                    flexDirection: 'row',
                    backgroundColor: '#fff',
                    borderRadius: pxToDp(10),
                    paddingLeft: pxToDp(3),
                    paddingRight: pxToDp(3),
                    margin: pxToDp(5),
                    alignItems: 'center',
                    alignSelf: 'center',
                  }}>
                  <IconFont style={{color: '#555'}} name="iconMap" />
                  <Text style={{color: '#555', marginLeft: pxToDp(5)}}>
                    {city}
                  </Text>
                </View>
              </View>
            </View>
          </TouchableOpacity>
        </LinearGradient>
        <View
          style={{
            flexDirection: 'row',
            backgroundColor: '#f7f7f7',
            justifyContent: 'space-around',
            padding: pxToDp(12),
          }}>
          <View
            style={{
              height: pxToDp(96),
              flex: 1,
              backgroundColor: '#fff',
              alignSelf: 'center',
              margin: pxToDp(4),
              borderRadius: pxToDp(12),
            }}>
            <TouchableOpacity
              onPress={() => this.context.navigate('LinkMan', 0)}
              style={{
                flex: 1,
                alignItems: 'flex-start',
                justifyContent: 'center',
                padding: pxToDp(24),
              }}>
              <Text style={{color: '#666', fontSize: pxToDp(22)}}>
                {eachLoveCount}
              </Text>
              <Text style={{color: '#666', fontSize: pxToDp(16)}}>
                互相关注
              </Text>
              <View
                style={{
                  position: 'absolute',
                  top: pxToDp(12),
                  right: pxToDp(12),
                }}>
                <IconFont
                  name="iconMutualFollow"
                  style={{fontSize: pxToDp(36), color: '#666'}}
                />
              </View>
            </TouchableOpacity>
          </View>
          <View
            style={{
              height: pxToDp(96),
              flex: 1,
              backgroundColor: '#fff',
              alignSelf: 'center',
              margin: pxToDp(4),
              borderRadius: pxToDp(12),
            }}>
            <TouchableOpacity
              onPress={() => this.context.navigate('LinkMan', 1)}
              style={{
                flex: 1,
                alignItems: 'flex-start',
                justifyContent: 'center',
                padding: pxToDp(24),
              }}>
              <Text style={{color: '#666', fontSize: pxToDp(22)}}>
                {loveCount}
              </Text>
              <Text style={{color: '#666', fontSize: pxToDp(16)}}>关注</Text>
              <View
                style={{
                  position: 'absolute',
                  top: pxToDp(12),
                  right: pxToDp(12),
                }}>
                <IconFont
                  name="iconFollow"
                  style={{fontSize: pxToDp(36), color: '#666'}}
                />
              </View>
            </TouchableOpacity>
          </View>
          <View
            style={{
              height: pxToDp(96),
              flex: 1,
              backgroundColor: '#fff',

              alignSelf: 'center',
              margin: pxToDp(4),
              borderRadius: pxToDp(12),
            }}>
            <TouchableOpacity
              onPress={() => this.context.navigate('LinkMan', 2)}
              style={{
                flex: 1,
                alignItems: 'flex-start',
                justifyContent: 'center',
                padding: pxToDp(24),
              }}>
              <Text style={{color: '#666', fontSize: pxToDp(22)}}>
                {fanCount}
              </Text>
              <Text style={{color: '#666', fontSize: pxToDp(16)}}>粉丝</Text>
              <View
                style={{
                  position: 'absolute',
                  top: pxToDp(12),
                  right: pxToDp(12),
                }}>
                <IconFont
                  name="iconFans"
                  style={{fontSize: pxToDp(36), color: '#666'}}
                />
              </View>
            </TouchableOpacity>
          </View>
        </View>
        <View>
          <ListItem
            leftIcon={
              <IconFont name="iconMutulFollow" style={{fontSize: pxToDp(36)}} />
            }
            title="我的订单"
            titleStyle={{color: '#666'}}
            bottomDivider
            chevron
            onPress={() => this.context.navigate('Order')}
          />
          <ListItem
            leftIcon={
              <IconFont name="iconMutulFollow" style={{fontSize: pxToDp(36)}} />
            }
            title="我的动态"
            titleStyle={{color: '#666'}}
            bottomDivider
            chevron
            onPress={() => this.context.navigate('DynamicState')}
          />
          <ListItem
            leftIcon={
              <IconFont name="iconMutulFollow" style={{fontSize: pxToDp(36)}} />
            }
            title="查看访客"
            titleStyle={{color: '#666'}}
            bottomDivider
            chevron
            onPress={() => this.context.navigate('Visitor')}
          />
          <ListItem
            leftIcon={
              <IconFont name="iconMutulFollow" style={{fontSize: pxToDp(36)}} />
            }
            title="专属客服"
            titleStyle={{color: '#666'}}
            bottomDivider
            chevron
            onPress={() => this.context.navigate('Service')}
          />
          <ListItem
            leftIcon={
              <IconFont name="iconSetting" style={{fontSize: pxToDp(36)}} />
            }
            title="设置"
            titleStyle={{color: '#666'}}
            bottomDivider
            chevron
            onPress={() => this.context.navigate('Setting')}
          />
        </View>
      </View>
    );
  }
}

export default Index;
