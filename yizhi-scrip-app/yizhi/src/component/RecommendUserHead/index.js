import React, {Component} from 'react';
import {View, Text, TouchableOpacity} from 'react-native';
import {pxToDp} from '@src/util/pxToDp';
import {NavigationContext} from '@react-navigation/native';
import IconFont from '../IconFont';
class Index extends Component {
  static contextType = NavigationContext;
  goPage = page => {
    this.context.navigate(page);
  };
  render() {
    return (
      <View
        style={{
          flexDirection: 'row',
          width: pxToDp(324),
          justifyContent: 'space-around',
        }}>
        <TouchableOpacity
          style={{alignItems: 'center'}}
          onPress={() => this.goPage('TestValue')}>
          <View
            style={{
              width: pxToDp(64),
              height: pxToDp(64),
              borderRadius: pxToDp(32),
              backgroundColor: '#15831C',
              justifyContent: 'center',
              alignItems: 'center',
            }}>
            <IconFont
              name="iconTestValue"
              style={{fontSize: pxToDp(46), color: '#fff'}}
            />
          </View>
          <Text
            style={{fontSize: pxToDp(16), marginTop: pxToDp(4), color: '#666'}}>
            测文值
          </Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={{alignItems: 'center'}}
          onPress={() => this.goPage('Near')}>
          <View
            style={{
              width: pxToDp(64),
              height: pxToDp(64),
              borderRadius: pxToDp(32),
              backgroundColor: '#FF6F00',
              justifyContent: 'center',
              alignItems: 'center',
            }}>
            <IconFont
              name="iconNear"
              style={{fontSize: pxToDp(38), color: '#fff'}}
            />
          </View>
          <Text
            style={{fontSize: pxToDp(16), marginTop: pxToDp(4), color: '#666'}}>
            附近的人
          </Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={{alignItems: 'center'}}
          onPress={() => this.goPage('Visitor')}>
          <View
            style={{
              width: pxToDp(64),
              height: pxToDp(64),
              borderRadius: pxToDp(32),
              backgroundColor: '#E32C2D',
              justifyContent: 'center',
              alignItems: 'center',
            }}>
            <IconFont
              name="iconVisitor"
              style={{fontSize: pxToDp(38), color: '#fff'}}
            />
          </View>
          <Text
            style={{fontSize: pxToDp(16), marginTop: pxToDp(4), color: '#666'}}>
            访客
          </Text>
        </TouchableOpacity>
      </View>
    );
  }
}

export default Index;
