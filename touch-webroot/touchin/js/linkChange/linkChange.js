/**
 * @author hcq
 * 
 * @param aSelectObjs  select的html dom对象数组
 * @param aData  联动数据
 * @param oChoice 可选项{choice:true,text:"请选择",value:-1}
 * @returns
 */
function LinkChange(aSelectObjs, aData, oChoice) {
    var thizObjs =  aSelectObjs || [];
	var depth = thizObjs.length;
	var _oChoice = oChoice || {};
	if (_oChoice.choice) {
		if (_oChoice.text==undefined) _oChoice.text = "请选择";
		if (_oChoice.value==undefined) _oChoice.value = "-1";
	}
	
	/**
	 *  levelObj = {N级的ID:[{N+1级的ID1,N+1级的ID1}，{N+1级的ID2,N+1级的ID2}]}
	 *  thizData = [levelObj1，levelObj2,...],N级联运就N-1个对象
	 */
	var thizData = new Array(depth-1); 
	
	if (aData) {//初始化
		for (var i=depth; i--; ) {
			 var currentObj = thizObjs[i];
			 currentObj._level = i;
			 currentObj._oldOnChange = currentObj.onchange;
			 currentObj.onchange = function() {
				linkEvent(this._level, this.value);
				if (this._oldOnChange) {
					this._oldOnChange();
				}
			 }
		}

		for (var i = aData.length; i--; ) {
			assembleData(0, aData[i]);
		}

		initOptions(aSelectObjs[0], aData);
		aSelectObjs[0].onchange();
	}

	this.setValue = function(aValueData) {
		if (aValueData) {
			for (var i=0, len=Math.min(aValueData.length,thizObjs.length); i<len; i++) {
				thizObjs[i].value=aValueData[i];
				thizObjs[i].onchange();
			}
		}
		return this;
	}
	
	/**
	 * 把列表组装成查询表(thizData)形式
	 */
	function assembleData(iLevel, oCurrLevelData) {
		if (iLevel<(depth-1) && oCurrLevelData) {
			thizData[iLevel] = thizData[iLevel] || {};
			var oLevelObject = thizData[iLevel];
			var children = oCurrLevelData.children || [];
			oLevelObject[oCurrLevelData.id] = children;
			var iNextLevel = iLevel+1;
			for (var i=children.length; i--; ) {
				assembleData(iNextLevel, children[i]);
			}
		}
	}

	/**
	 * 联动事件
	 */
	function linkEvent(iLevel, vValue) {
		if (iLevel<(depth-1)) {
			var currentObj = thizObjs[iLevel+1];
			initOptions(currentObj, thizData[iLevel][vValue]);
			currentObj.onchange();
		}
	}
	
	/**
	 * 初始化select的options
	 */
	function initOptions(obj, aData) {
		if (obj) {
			var optionsObj = obj.options;
			optionsObj.length=0;
			if (_oChoice.choice) {
				optionsObj.add(new Option(_oChoice.text,_oChoice.value));
			}
			if (aData) {
				for (var idx=0, loop=aData.length;idx<loop ; idx++) {
					var node = aData[idx];
					if (node) {
						optionsObj.add(new Option(node.name,node.id));
					}
				}
			}
		}
	}
	
	return this;
}