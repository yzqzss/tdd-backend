package com.bunnyxt.tdd.controller.user;

import com.bunnyxt.tdd.auth.TddAuthUtil;
import com.bunnyxt.tdd.error.InvalidRequestParameterException;
import com.bunnyxt.tdd.model.user.User;
import com.bunnyxt.tdd.model.user.UserSignIn;
import com.bunnyxt.tdd.service.user.UserSignInService;
import com.bunnyxt.tdd.util.TddParamCheckUtil;
import com.bunnyxt.tdd.util.TddResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class UserSignInRestController {

    @Autowired
    UserSignInService userSignInService;

    // user ============================================================================================================

    // user sign in
    // TODO
    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/user/signin", method = RequestMethod.POST)
    public void postUserSignIn() {
        // get userid
        Long userid = TddAuthUtil.GetCurrentUser().getId();
        userSignInService.postUserSignIn(userid);
    }

    // check user's sign in history
    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/user/signin/me", method = RequestMethod.GET)
    public ResponseEntity<List<UserSignIn>> queryUserSignInsMe(@RequestParam(defaultValue = "0") Integer last_count,
                                                               @RequestParam(defaultValue = "0") Integer start_ts,
                                                               @RequestParam(defaultValue = "0") Integer end_ts,
                                                               @RequestParam(defaultValue = "1") Integer pn,
                                                               @RequestParam(defaultValue = "100") Integer ps)
            throws InvalidRequestParameterException {
        // get userid
        Long userid = TddAuthUtil.GetCurrentUser().getId();

        return queryUserSignIns(userid, last_count, start_ts, end_ts, pn, ps);
    }

    // admin ===========================================================================================================

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/user/signin", method = RequestMethod.GET)
    public ResponseEntity<List<UserSignIn>> queryUserSignIns(@RequestParam(defaultValue = "0") Long userid,
                                                             @RequestParam(defaultValue = "0") Integer last_count,
                                                             @RequestParam(defaultValue = "0") Integer start_ts,
                                                             @RequestParam(defaultValue = "0") Integer end_ts,
                                                             @RequestParam(defaultValue = "1") Integer pn,
                                                             @RequestParam(defaultValue = "100") Integer ps)
            throws InvalidRequestParameterException {
        // check params
        TddParamCheckUtil.userid(userid);
        TddParamCheckUtil.last_count(last_count, 100);
        TddParamCheckUtil.start_ts(start_ts);
        TddParamCheckUtil.end_ts(end_ts);
        TddParamCheckUtil.pn(pn);
        TddParamCheckUtil.ps(ps, 100);

        // get list
        List<UserSignIn> list = userSignInService.queryUserSignIns(userid, last_count, start_ts, end_ts, pn, ps);

        // get total count
        Integer totalCount = userSignInService.queryUserSignInsCount(userid, start_ts, end_ts);

        return TddResponseUtil.AssembleList(list, totalCount);
    }

}
