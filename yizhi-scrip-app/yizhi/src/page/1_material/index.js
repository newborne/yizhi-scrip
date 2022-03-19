import React, {Component} from 'react';
import {Image, View, Text, StatusBar, TouchableOpacity} from 'react-native';
import {ImageHeaderScrollView} from 'react-native-image-header-scroll-view';

import MaterialHead from '@src/component/MaterialHead';
import Date from '@src/util/Date';
import {pxToDp} from '@src/util/pxToDp';

class Index extends Component {
  state = {
    list: [
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
          title: '敬业',
          content: '敬字工夫，乃是圣门第一义。（朱熹）',
          createTime: '2022-03-18T01:50:58.000Z',
        },
      },
      {
        news: {
          logo: '',
          title: '诚信',
          content:
            '诚信是道路，随着开拓者的脚步延伸；诚信是智慧，随着博学者的求索积累；',
          createTime: '2022-03-18T01:50:58.000Z',
        },
      },
    ],
  };
  render() {
    const {list} = this.state;
    return (
      <ImageHeaderScrollView
        maxHeight={pxToDp(200)}
        minHeight={pxToDp(80)}
        headerImage={require('@src/res/image/material_head.png')}
        renderForeground={() => (
          <View
            style={{
              height: pxToDp(130),
              justifyContent: 'center',
              alignItems: 'center',
            }}>
            <StatusBar backgroundColor={'transparent'} translucent={true} />
            <MaterialHead />
          </View>
        )}>
        <View style={{backgroundColor: '#f7f7f7'}}>
          <View
            style={{
              height: pxToDp(48),
              flexDirection: 'row',
              justifyContent: 'center',
              alignItems: 'center',
              marginBottom: pxToDp(4),
              backgroundColor: '#fff',
            }}>
            <Text style={{color: '#333', fontSize: pxToDp(24)}}>推荐</Text>
          </View>
          <View style={{marginBottom: pxToDp(12)}}>
            {list.map((v, i) => (
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
        </View>
      </ImageHeaderScrollView>
    );
  }
}

export default Index;
