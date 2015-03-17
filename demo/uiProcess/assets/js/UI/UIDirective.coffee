###

  ui的指令
  创建人：Porry Chen
  创建日期：2015-02-21

###

'use strict';

angular.module('app.ui.directives', [])

# uiProcess 进度图的展现
.directive('uiProcess', [ ->
    compilationFunction = (templateElement, templateAttributes, transclude) ->
      if templateElement.length == 1
        # 获得原始元素
        node = templateElement[0]
        height = node.getAttribute('data-process-height') || '90'
        # 创建svg画布
        svg = document.createElementNS('http://www.w3.org/2000/svg','svg')
        svg.setAttribute('xmlns', 'http://www.w3.org/2000/svg')
        svg.setAttribute('version', 1.1)
        svg.setAttribute('width', '100%')
        svg.setAttribute('height', height)
        svg.setAttribute('data-process-model', node.getAttribute('data-process-model'));
        # 原始元素替换为svg画布
        node.parentNode.replaceChild(svg, node);

        return {
          pre: preLink = (scope, instanceElement, instanceAttributes, controller) ->
            # 获得数据
            nodedata = svg.getAttribute('data-process-model')

            # return fill color
            getFillColor = (type, rollback = 0) ->
              result = '#7b7b7b'

              if rollback > 0
                result = 'red'
              else if type == 'ok'
                result = '#669e41'
              else if type == 'wait'
                result = '#5b9bd5'

              return result

            # 监听数据
            scope.$watch(nodedata, (newValue, oldValue) ->
              # 重置画布的高度
              svg.setAttribute('height', height)
              # 清空画布子元素
              removeAllLength = svg.childNodes.length
              while removeAllLength != 0
                svg.removeChild(svg.lastChild)
                removeAllLength = svg.childNodes.length

              # 判断数据对象是否定义
              if newValue == undefined
                return

              #nodes = [] 暂时不使用

              count = 0
              isChild = false
              childCount = 0
              gap = newValue.gap || 200 
              line = null
              # 进度线数组
              lines = []

              # 返回节点圆形
              getCircle = (data, index, length) ->
                circle = document.createElementNS('http://www.w3.org/2000/svg', 'circle')
                circle.setAttribute('cx', 40 + count * gap)
                circle.setAttribute('cy', height / 2 + (if childCount > 2 and isChild then childCount - 2 else 0) * 90)
                circle.setAttribute('r', 30)
                # 设置圆形边宽
                circle.setAttribute('stroke-width', 6)
                # 设置图开边色
                if data.type == 'ok'
                  if count == 0
                    circle.setAttribute('stroke', '#92c570')
                  else
                    circle.setAttribute('stroke','#669e41')
                else if data.type == 'wait'
                  circle.setAttribute('stroke', '#5b9bd5')
                else
                  if isChild or index + 1 != length
                    circle.setAttribute('stroke', '#7b7b7b')
                  else
                    circle.setAttribute('stroke', '#515151')
                # 设置圆形背景色
                circle.setAttribute('fill', getFillColor(data.type))
                #if data.message != ''
                #  circle.onmousedown = () ->
                #    alert(data.message);

                return circle

              # 返回带箭头进度线
              getArrow = (type, index, childIndex) ->
                rollback = 0
                path = document.createElementNS('http://www.w3.org/2000/svg', 'path')
                switch type
                  # 开始分支线
                  when 2
                    path.setAttribute('d', 'm' + (73 + (index - 1) * gap) + ',' + (height / 2 - 3) + 'h' + ((gap - 74) / 2 + 4) + 'v' + childIndex * 90 + 'h' + ((gap - 74) / 2 - 4) + 'v-6l8,10l-8,10v-6h-' + ((gap - 74) / 2 + 4) + 'v-' + childIndex * 90 + 'h-' + ((gap - 74) / 2 - 4) + 'z')
                  # 结束分支线
                  when 3 then path.setAttribute('d', 'm' + (73 + index * gap) + ',' + (height / 2 - 3 + childIndex * 90) + 'h' + ((gap - 74) / 2 - 4) + 'v-' + childIndex * 90 + 'h' + ((gap - 74) / 2 + 4) + 'v-6l8,10l-8,10v-6h-' + ((gap - 74) / 2 - 4) + 'v' + childIndex * 90 + 'h-' + ((gap - 74) / 2 + 4) + 'z')
                  # 开始分支返回
                  when 4
                    path.setAttribute('d', 'm' + (7 + index * gap) + ',' + (height / 2 - 3 + childIndex * 90) + 'h-' + ((gap - 74) / 2 + 4) + 'v-' + childIndex * 90 + 'h-' + ((gap - 74) / 2 - 4) + 'v-6l-8,10l8,10v-6h' + ((gap - 74) / 2 - 12) + 'v' + childIndex * 90 + 'h' + ((gap - 74) / 2 + 12) + 'z')
                    rollback = 1
                  # 前进线返回
                  when 5
                    path.setAttribute('d', 'm' + (7 + index * gap) + ',' + (height / 2 - 3 + childIndex * 90) + 'h-' + (gap - 74) + 'v-6l-8,10l8,10v-6h' + (gap - 74) + 'z')
                    rollback = 1
                  # 正常前进线
                  else
                    path.setAttribute('d', 'm' + (73 + index * gap) + ',' + (height / 2 - 3 + childIndex * 90) + 'h' + (gap - 60 - 6 - 8) + 'v-6l8,10l-8,10v-6h-' + (gap - 60 - 6 - 8) + 'z')
                path.setAttribute('stroke-opacity', 'null')
                path.setAttribute('stroke-width', 0)
                path.setAttribute('fill', getFillColor(type, rollback))

                return path

              # 返回进度线，调用getArrow方法
              getLine = (type) ->
                childIndex = 0
                switch type
                  # 开始分支线
                  when 2, 3, 4
                    childIndex = childCount - 2
                  else
                    childIndex = if childCount > 2 and isChild then childCount - 2 else 0

                return getArrow(type, count, childIndex)

              # lines数组中加入进度线数据信息
              addLine = (type, index) ->
                info = {}
                info.type = type
                info.count = count
                info.childCount = childCount
                info.index = index
                info.isChild = isChild

                lines.push(info)

                return info;

              # 改变lines数据中进度线的方向与颜色
              changeLine = (rollback, index) ->
                if rollback < -1
                  # 顺序从前到后循环进度条数组
                  angular.forEach(lines, (info, loopIndex) ->
                    # 判断是否有分支                    
                    if (isChild and (index + rollback < -1)) or (!isChild and (count + rollback < -1))
                      # 重新设置回退数
                      if count + rollback <= 0 then rollback = - count + 1
                      # 判断进度信息是否满足变更条件
                      if rollback < -1 and info.count - count == rollback
                        if info.count > 0
                          info.type = 5
                          info.count += 1
                        rollback++
                    else if isChild and (index + rollback >= -1)
                      if childCount == info.childCount
                        # 分支节点里进度信息的判断
                        ipos = info.index - index
                        if ipos <= -1 and (ipos > rollback or (info.index ==0 and ipos == rollback and info.type != 2))
                          if info.type == 2
                            info.type = 4
                          else
                            info.type = 5
                            info.count += 1
                      else
                        # 第一条分支节点的特殊判断
                        if childCount == 2 and info.count - count == rollback
                          info.type = 5
                          info.count += 1
                  )

              # 进度循环主函数
              loopProcessData = (newValue) ->
                value = if newValue.nodes then newValue.nodes else newValue

                # 循环画布数据
                angular.forEach(value, (data, index) ->
                  if angular.isArray(data)
                    isChild = true
                    childCount++
                    # 数据递归循环
                    index += loopProcessData(data)
                    # 循环后须要减1
                    count--
                    isChild = false
                  else
                    data.rollback = data.rollback || 0

                    # 分支折线
                    if childCount > 2 and index == 0
                      count -= value.length
                      changeLine(data.rollback, index)
                      if data.rollback < 0
                        svg.appendChild(getLine(4))
                      else
                        addLine(2, index)

                    # 是否加入顺序线
                    if line
                      if data.rollback < 0 and !(childCount > 2 and index == 0)
                        # 判断回退步数是否正确，可纠正
                        if !isChild and (count + data.rollback <= 0) then data.rollback = - count + 1
                        # 从进度线数组中移除数据
                        if data.rollback == -1 then lines.pop()
                        # 改变进度线的方向与颜色
                        changeLine(data.rollback, index)
                        # 加入回退的顺序线
                        if (!isChild and count > 1) or isChild then svg.appendChild(getLine(5))
                      line = null

                    # 把圆形与文字放入组中
                    g = document.createElementNS('http://www.w3.org/2000/svg', 'g')
                    if data.message != ''
                      g.setAttribute('data-rel', 'tooltip')
                      g.setAttribute('title', data.message)

                    svg.appendChild(g)

                    # add circle
                    g.appendChild(getCircle(data, index, value.length))

                    # add text
                    text = document.createElementNS('http://www.w3.org/2000/svg', 'text')
                    text.setAttribute('x', 26 + count * gap)
                    text.setAttribute('y', height / 2 + 5 + (if childCount > 2 and isChild then childCount - 2 else 0) * 90)
                    text.setAttribute('style', 'font-family:Verdana;font-size:14;fill:white')
                    text.textContent = data.label
                    g.appendChild(text)
 
                    # add line
                    if isChild or index + 1 != value.length
                      if childCount > 2 and index + 1 == value.length
                        svg.appendChild(getLine(3))
                      else
                        line = addLine(1, index)

                  count++
                )
              
              # 当根节点有数据时，才循环
              if newValue.nodes
                # 调用节点生成函数
                loopProcessData(newValue)

                # 循环数据生成进度线
                angular.forEach(lines, (info, index) ->
                  childIndex = 0
                  switch info.type
                    # 开始分支线
                    when 2, 3, 4
                      childIndex = info.childCount - 2
                    else
                      childIndex = if info.childCount > 2 and info.isChild then info.childCount - 2 else 0

                  # 画布中加入进度条
                  svg.appendChild(getArrow(info.type, info.count, childIndex))
                )

                # 调整整个画布的高度
                if childCount > 2 and height < (childCount - 1) * 90
                  svg.setAttribute('height', (childCount - 1) * 90)
                # 调整整个画布的宽度
                svg.setAttribute('width', 40 * 2 + (count - 1) * gap)
            )
            
        }

    return {
        compile: compilationFunction
        replace: true
    }
])