const CHIP_ATTRS = ['cashChips','mudChips','promoChips','quidsChips'];
/**
 * 筹码转换成panel可以识别的数据
 * @param {*} chips 
 */
export function cToPanelDataList( chips ){
  if( !Array.isArray(chips)){
    return [];
  }  
  return chips.map( chip => {
    let dataList = [];
    Object.keys(chip).forEach(key => {
      if(~CHIP_ATTRS.indexOf(key) ){
        let rs = chip[key];
        if(Array.isArray(rs) && rs.length){
          dataList.push({
            key:key,
            list:rs
          });
        }
      }
    });
    let span = parseInt(24 / dataList.length);
    return {
      __source__:chip,
      coinTypeId:chip.coinType.coinTypeId,
      coinCode:chip.coinType.coinType,
      total:chip.total,
      dataList:dataList.map(d=>{
        d.span = span;
        return d;
      }),
      simple:chip.simpleTotalChips
    }
  } );
}