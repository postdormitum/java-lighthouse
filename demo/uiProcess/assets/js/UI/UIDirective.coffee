'use strict';

angular.module('app.ui.directives', [])

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
              #nodes = [] 暂时不使用
              count = 0
              isChild = false
              childCount = 0
              gap = newValue.gap || 200 
              line = null

              # 返回进度线
              getArrow = (type) ->
                rollback = 0
                path = document.createElementNS('http://www.w3.org/2000/svg', 'path')
                switch type
                  # 开始分支线
                  when 2
                    path.setAttribute('d', 'm' + (73 + (count - 1) * gap) + ',' + (height / 2 - 3) + 'h' + ((gap - 74) / 2 + 4) + 'v' + (childCount - 2) * 90 + 'h' + ((gap - 74) / 2 - 4) + 'v-6l8,10l-8,10v-6h-' + ((gap - 74) / 2 + 4) + 'v-' + (childCount - 2) * 90 + 'h-' + ((gap - 74) / 2 - 4) + 'z')
                  # 结束分支线
                  when 3 then path.setAttribute('d', 'm' + (73 + count * gap) + ',' + (height / 2 - 3 + (childCount - 2) * 90) + 'h' + ((gap - 74) / 2 - 4) + 'v-' + (childCount - 2) * 90 + 'h' + ((gap - 74) / 2 + 4) + 'v-6l8,10l-8,10v-6h-' + ((gap - 74) / 2 - 4) + 'v' + (childCount - 2) * 90 + 'h-' + ((gap - 74) / 2 + 4) + 'z')
                  # 开始分支返回
                  when 4
                    path.setAttribute('d', 'm' + (7 + count * gap) + ',' + (height / 2 - 3 + (childCount - 2) * 90) + 'h-' + ((gap - 74) / 2 + 4) + 'v-' + (childCount - 2) * 90 + 'h-' + ((gap - 74) / 2 - 4) + 'v-6l-8,10l8,10v-6h' + ((gap - 74) / 2 - 12) + 'v' + (childCount - 2) * 90 + 'h' + ((gap - 74) / 2 + 12) + 'z')
                    rollback = 1
                  # 前进线返回
                  when 5
                    path.setAttribute('d', 'm' + (7 + count * gap) + ',' + (height / 2 - 3 + (if childCount > 2 and isChild then childCount - 2 else 0) * 90) + 'h-' + (gap - 74) + 'v-6l-8,10l8,10v-6h' + (gap - 74) + 'z')
                    rollback = 1
                  # 正常前进线
                  else
                    path.setAttribute('d', 'm' + (73 + count * gap) + ',' + (height / 2 - 3 + (if childCount > 2 and isChild then childCount - 2 else 0) * 90) + 'h' + (gap - 60 - 6 - 8) + 'v-6l8,10l-8,10v-6h-' + (gap - 60 - 6 - 8) + 'z')
                path.setAttribute('stroke-opacity', 'null')
                path.setAttribute('stroke-width', 0)
                path.setAttribute('fill', getFillColor(type, rollback))

                return path

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

              # 进度循环主函数
              loopProcessData = (newValue) ->
                value = if newValue.nodes then newValue.nodes else newValue
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

                    if childCount > 2 and index == 0
                      count -= value.length
                      svg.appendChild(getArrow(if data.rollback > 0 then 4 else 2))
                    if line
                      if data.rollback > 0 and !(childCount == 3 and index == 0)
                        svg.appendChild(getArrow(5))
                      else
                        svg.appendChild(line)
                      line = null

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
                        svg.appendChild(getArrow(3))
                      else
                        line = getArrow(1)

                  count++
                )

              # 调用节点生成函数
              loopProcessData(newValue)
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