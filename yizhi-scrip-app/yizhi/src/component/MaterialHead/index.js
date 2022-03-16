import React, {Component} from 'react';
import {View, Text, TouchableOpacity} from 'react-native';
import {NavigationContext} from '@react-navigation/native';
import {pxToDp} from '@src/util/pxToDp';
import IconFont from '@src/component/IconFont';
class Index extends Component {
  static contextType = NavigationContext;
  render() {
    return (
      <View
        style={{
          marginTop: pxToDp(108),
          flexDirection: 'row',
          width: pxToDp(324),
          justifyContent: 'space-around',
        }}>
        <TouchableOpacity
          style={{alignItems: 'center'}}
          onPress={() => this.context.navigate('News')}>
          <View
            style={{
              width: pxToDp(64),
              height: pxToDp(64),
              borderRadius: pxToDp(32),
              backgroundColor: '#FC714D',
              justifyContent: 'center',
              alignItems: 'center',
            }}>
            <IconFont
              name="iconNews"
              style={{fontSize: pxToDp(32), color: '#fff'}}
            />
          </View>
          <Text
            style={{fontSize: pxToDp(16), marginTop: pxToDp(4), color: '#fff'}}>
            今日热点
          </Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={{alignItems: 'center', padding: pxToDp(14)}}
          onPress={() => this.context.navigate('Scrip')}>
          <View
            style={{
              width: pxToDp(64),
              height: pxToDp(64),
              borderRadius: pxToDp(32),
              backgroundColor: '#715CFF',
              justifyContent: 'center',
              alignItems: 'center',
            }}>
            <IconFont
              name="iconScrip"
              style={{fontSize: pxToDp(38), color: '#fff'}}
            />
          </View>
          <Text
            style={{fontSize: pxToDp(16), marginTop: pxToDp(4), color: '#fff'}}>
            纸 条
          </Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={{alignItems: 'center'}}
          onPress={() => this.context.navigate('MaterialType')}>
          <View
            style={{
              width: pxToDp(64),
              height: pxToDp(64),
              borderRadius: pxToDp(32),
              backgroundColor: '#31BF1D',
              justifyContent: 'center',
              alignItems: 'center',
            }}>
            <IconFont
              name="iconSort"
              style={{fontSize: pxToDp(32), color: '#fff'}}
            />
          </View>
          <Text
            style={{fontSize: pxToDp(16), marginTop: pxToDp(4), color: '#fff'}}>
            分类素材
          </Text>
        </TouchableOpacity>
      </View>
    );
  }
}

export default Index;
